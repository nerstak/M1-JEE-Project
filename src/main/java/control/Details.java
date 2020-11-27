package control;

import control.session_beans.*;
import models.*;
import utils.ProcessString;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.UUID;

import static utils.Constants.CONTROLLER_HOMEPAGE;
import static utils.Constants.MISSION_PAGE;

/**
 * Details controller, for any additional information on internship
 */
@WebServlet(name = "Details")
public class Details extends ServletModel {
    @EJB
    private InternshipSessionBean internshipsSB;
    @EJB
    private KeywordsSessionBean keywordsSB;
    @EJB
    private SkillsSessionBean skillsSB;
    @EJB
    private StudentSessionBean studentSB;
    @EJB
    private MarksSessionBean marksSB;
    @EJB
    private VisitSessionBean visitSB;
    @EJB
    private FinalReportSessionBean finalReportSB;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Check from which submit button the request come from
        String internshipSubmit = request.getParameter("internshipSubmit");
        String internshipId = request.getParameter("internshipId");
        InternshipEntity internshipEntity;

        if (internshipSubmit.equals("details")) {
            internshipEntity = internshipsSB.find(UUID.fromString(internshipId));

            //Set request attributes
            request.setAttribute("internshipData", internshipEntity);
            request.setAttribute("listOfSkills", skillsSB.getSkills());
            request.setAttribute("listOfKeywords",keywordsSB.getKeywords());

            forward(request,response,MISSION_PAGE);
        } else if (internshipSubmit.equals("modify")) {
            internshipEntity = internshipsSB.find(UUID.fromString(internshipId));

            updateAllData(request, internshipEntity);
            redirect(response,CONTROLLER_HOMEPAGE);
        } else {
            redirect(response,CONTROLLER_HOMEPAGE);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        redirect(response,CONTROLLER_HOMEPAGE);
    }

    /**
     * Update all data for one internship
     * @param request, http request object
     * @param internshipEntity
     * @return true if the db is updated
     */
    private boolean updateAllData(HttpServletRequest request, InternshipEntity internshipEntity){
        //Check if update is done, if it not return false
        if (!updateInternship(request, internshipEntity)){
            return false;
        }

        if (!updateStudent(request, internshipEntity)){
            return false;
        }

        if (!updateMarks(request, internshipEntity)){
            return false;
        }

        if (!updateVisit(request, internshipEntity)){
            return false;
        }

        if (!updateFinalReport(request, internshipEntity)){
            return false;
        }

        return true;
    }

    /**
     * Update the student
     * @param request, http request object
     * @param internshipEntity
     * @return true if the db is updated
     */
    private boolean updateStudent(HttpServletRequest request, InternshipEntity internshipEntity){
        //Student
        String studentGroup = request.getParameter("studentGroup");
        String studentFirstname = request.getParameter("studentFirstname");
        String studentName = request.getParameter("studentName");

        StudentEntity student = internshipEntity.getStudent();

        if(ProcessString.areStringEmpty(studentFirstname, studentGroup, studentName) || student == null){
            return false;
        }

        student.setStudentGroup(studentGroup);
        student.setFirstname(studentFirstname);
        student.setName(studentName);
        studentSB.save(student);

        return true;
    }

    /**
     * Update the marks
     * @param request, http request object
     * @param internshipEntity
     * @return true if the db is updated
     */
    private boolean updateMarks(HttpServletRequest request, InternshipEntity internshipEntity){
        //Marks
        String commMark = request.getParameter("commMark");
        String techMark = request.getParameter("techMark");

        if(ProcessString.areStringEmpty(commMark, techMark)){
            return false;
        }

        if((Integer.parseInt(commMark) < 0) || (Integer.parseInt(commMark) > 20)){
            return false;
        }

        if((Integer.parseInt(techMark) < 0) || (Integer.parseInt(techMark) > 20)){
            return false;
        }

        MarksEntity marks = internshipEntity.getMarks();
        marks.setCommunication(Integer.valueOf(commMark));
        marks.setTech(Integer.valueOf(techMark));
        marksSB.save(marks);

        return true;
    }

    /**
     * Update the visit
     * @param request, http request object
     * @param internshipEntity
     * @return true if the db is updated
     */
    private boolean updateVisit(HttpServletRequest request, InternshipEntity internshipEntity){
        //Visit
        String visitPlanned = request.getParameter("visitPlanned")== null
                ? "false"
                : "true";
        String visitDone = request.getParameter("visitDone")== null
                ? "false"
                : "true";
        VisitEntity visit = internshipEntity.getVisit();

        if(ProcessString.areStringEmpty(visitDone, visitPlanned)){
            return false;
        }
        visit.setDone(Boolean.valueOf(visitDone));
        visit.setPlanned(Boolean.valueOf(visitPlanned));
        visitSB.save(visit);

        return true;
    }

    /**
     * Update the internship
     * @param request, http request object
     * @param internshipEntity
     * @return true if the db is updated
     */
    private boolean updateInternship(HttpServletRequest request, InternshipEntity internshipEntity){
        //Internship
        String beginningDate = request.getParameter("beginningDate");
        String endDate = request.getParameter("endDate");
        String supervisor = request.getParameter("supervisor");
        String defense = request.getParameter("defense")== null
                ? "false"
                : "true";
        String webSurvey = request.getParameter("webSurvey")== null
                ? "false"
                : "true";
        String companyEval = request.getParameter("companyEval")== null
                ? "false"
                : "true";
        String cdc = request.getParameter("cdc") == null
                ? "false"
                : "true";
        String internshipId = request.getParameter("internshipId");

        //Check if all data are not empty and begin date is before end date
        if((ProcessString.areStringEmpty(beginningDate, endDate, supervisor, defense, webSurvey, companyEval, cdc, internshipId)) || (ProcessString.isDateBefore(endDate, beginningDate))) {
            return false;
        }

        internshipEntity.setDefense(Boolean.valueOf(defense));
        internshipEntity.setCompanyEval(Boolean.valueOf(companyEval));
        internshipEntity.setWebSurvey(Boolean.valueOf(webSurvey));
        internshipEntity.setCdc(Boolean.valueOf(cdc));
        internshipEntity.setInternSupervisor(supervisor);
        internshipEntity.setBeginning(Date.valueOf(beginningDate));
        internshipEntity.setEnding(Date.valueOf(endDate));
        internshipsSB.save(internshipEntity);

        return true;
    }

    /**
     * Update the report table
     * @param request, http request
     * @param internshipEntity
     * @return true if the database has been updated
     */
    private boolean updateFinalReport(HttpServletRequest request, InternshipEntity internshipEntity){
        //Report
        String report = request.getParameter("releasedReport") == null
                ? "false"
                : "true";

        if (ProcessString.areStringEmpty(report)){
            return false;
        }
        FinalReportEntity finalReport = internshipEntity.getFinalReport();
        finalReport.setReport(Boolean.valueOf(report));
        finalReportSB.save(finalReport);

        return true;
    }
}

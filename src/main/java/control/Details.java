package control;

import model.*;
import utils.MarksDataServices;
import utils.ProcessString;
import utils.database.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

import static utils.Constants.*;

/**
 * Details controller, for any additional information on internship
 */
@WebServlet(name = "Details")
public class Details extends ServletModel {
    private InternshipData internshipData;

    private InternshipDataServices internshipDataServices;
    private SkillsDataServices skillsDataServices;
    private KeywordsDataServices keywordsDataServices;
    private StudentDataServices studentDataServices;
    private FinalReportDataServices finalReportDataServices;
    private MarksDataServices marksDataServices;
    private VisitDataServices visitDataServices;
    private boolean successRequest;

    @Override
    public void init() {
        super.init();
        internshipDataServices = new InternshipDataServices(dbUser, dbPwd, dbUrl);
        skillsDataServices = new SkillsDataServices(dbUser, dbPwd, dbUrl);
        keywordsDataServices = new KeywordsDataServices(dbUser, dbPwd, dbUrl);
        studentDataServices = new StudentDataServices(dbUser, dbPwd, dbUrl);
        finalReportDataServices = new FinalReportDataServices(dbUser, dbPwd, dbUrl);
        marksDataServices = new MarksDataServices(dbUser, dbPwd, dbUrl);
        visitDataServices = new VisitDataServices(dbUser, dbPwd, dbUrl);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Check from which submit button the request come from
        String internshipSubmit = request.getParameter("internshipSubmit");
        if (internshipSubmit.equals("details")) {
            String internshipId = request.getParameter("internshipId");
            internshipData = internshipDataServices.getInternshipDetailed(internshipId);

            //Set request attributes
            request.setAttribute("internshipData", internshipData);
            request.setAttribute("listOfSkills", skillsDataServices.getListOfSkills());
            request.setAttribute("listOfKeywords",keywordsDataServices.getListOfKeywords());
            request.setAttribute("listOfStudentSkills", skillsDataServices.getStudentSkillsAll(internshipData.getStudent()));
            request.setAttribute("listOfInternshipKeywords", keywordsDataServices.getInternshipKeywordsAll(internshipData.getInternship().getInternship().toString()));

            request.getRequestDispatcher(MISSION_PAGE).forward(request, response);
        } else if (internshipSubmit.equals("modify")) {
            if(updateAllData(request)){
                //Todo add succes bd update message + display it in homepage
                //Call homepage servlet to retrieve all data
                response.sendRedirect("Homepage");
            }else{
                //Todo add error bd update message + display it in homepage
                //Call homepage servlet to retrieve all data
                response.sendRedirect("Homepage");
            }
        } else {
            response.sendRedirect("Homepage");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("Homepage");
    }

    /**
     * Update all data for one internship
     * @param request, http request object
     * @return true if the db is updated
     */
    private boolean updateAllData(HttpServletRequest request){
        //Disable all data services
        DataServices.disableAutoCommits(studentDataServices, marksDataServices, visitDataServices, internshipDataServices);

        //Check if update is done, if it not return false
        if (!updateStudent(request)){
            return false;
        }

        if (!updateMarks(request)){
            return false;
        }

        if (!updateVisit(request)){
            return false;
        }

        if (!updateInternship(request)){
            return false;
        }


        //todo : add reports info to each internship (in InternshipDataServices)
        //todo : update report info in the bdd
        //Report
//        String report = request.getParameter("releasedReport").equals("on")
//                ? "true"
//                : "false";
//        String reportId = request.getParameter("finalReportId");


        //Commit all request in the db
        DataServices.commitRequest(studentDataServices, marksDataServices, visitDataServices, internshipDataServices);
        return true;
    }

    /**
     * Update the student
     * @param request, http request object
     * @return true if the db is updated
     */
    private boolean updateStudent(HttpServletRequest request){
        //Student
        String studentGroup = request.getParameter("studentGroup");
        String studentFirstname = request.getParameter("studentFirstname");
        String studentName = request.getParameter("studentName");
        String studentId = request.getParameter("studentId");

        if(ProcessString.areStringEmpty(studentFirstname, studentGroup, studentName, studentId)){
            return false;
        }

        int rowAffectedCompany = studentDataServices.updateNamesGroup(studentName, studentFirstname, studentGroup, studentId);
        return (rowAffectedCompany == 1);
    }

    /**
     * Update the marks
     * @param request, http request object
     * @return true if the db is updated
     */
    private boolean updateMarks(HttpServletRequest request){
        //Marks
        String commMark = request.getParameter("commMark");
        String techMark = request.getParameter("techMark");
        String marksId = request.getParameter("marksId");

        if(ProcessString.areStringEmpty(commMark, techMark, marksId)){
            return false;
        }

        int rowAffectedCompany = marksDataServices.updateMarks(techMark, commMark, marksId);
        return (rowAffectedCompany == 1);
    }

    /**
     * Update the visit
     * @param request, http request object
     * @return true if the db is updated
     */
    private boolean updateVisit(HttpServletRequest request){
        //Visit
        String visitPlanned = request.getParameter("visitPlanned")== null
                ? "false"
                : "true";
        String visitDone = request.getParameter("visitDone")== null
                ? "false"
                : "true";
        String visitId = request.getParameter("visitId");

        if(ProcessString.areStringEmpty(visitDone, visitPlanned, visitId)){
            return false;
        }

        int rowAffectedCompany = visitDataServices.updateVisit(visitDone, visitPlanned, visitId);
        return (rowAffectedCompany == 1);
    }

    /**
     * Update the internship
     * @param request, http request object
     * @return true if the db is updated
     */
    private boolean updateInternship(HttpServletRequest request){
        //Internship
        String beginingDate = request.getParameter("beginingDate");
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
        if((ProcessString.areStringEmpty(beginingDate, endDate, supervisor, defense, webSurvey, companyEval, cdc, internshipId)) || (ProcessString.isDateBefore(endDate, beginingDate))) {
            return false;
        }

        int rowAffectedCompany = internshipDataServices.updateInternshipFromHomepage(
                Date.valueOf(beginingDate), Date.valueOf(endDate),
                supervisor, defense, webSurvey, companyEval, cdc, internshipId);

        return (rowAffectedCompany == 1);
    }
}

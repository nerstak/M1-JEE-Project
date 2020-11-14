package control;

import model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import static utils.Constants.*;

@WebServlet(name = "Details")
public class Details extends ServletModel {
    private InternshipData internshipData;
    private ResultSet rs;
    private ArrayList<Skills> listOfSkills;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Check from which submit button the request come from
        String internshipSubmit = request.getParameter("internshipSubmit");
        if (internshipSubmit.equals("details")){
            String internshipId = request.getParameter("internshipId");
            internshipData = getInternshipDataDetails(internshipId);

            //Set request attributes
            request.setAttribute("internshipData", internshipData);
            request.setAttribute("listOfStudentSkills", getListOfStudentSkills(internshipData.getStudent().getStudentId().toString()));
            request.getRequestDispatcher(MISSION_PAGE).forward(request, response);
        }else if (internshipSubmit.equals("modify")){

        }else{
            //todo je pense qu'il n'y a plus la liste des internship à renvoyer
            request.getRequestDispatcher(HOME_PAGE).forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * Get all the details about an internship for one student
     * @param internshipId, the student ID in the database
     * @return internshipData that contains all the data
     */
    public InternshipData getInternshipDataDetails(String internshipId){
        internshipData = new InternshipData();
        rs = dataServices.selectInternshipDetailed(internshipId);

        if (rs != null){
            try {
                while (rs.next()){
                    //Instantiate a student bean
                    Student student = new Student();
                    student.setStudentId(UUID.fromString(rs.getString("student_id")));
                    student.setName(rs.getString("name"));
                    student.setFirstName(rs.getString("firstname"));
                    student.setEmail(rs.getString("email"));
                    student.setGroup(rs.getString("group"));
                    student.setLinkedinProfile(rs.getString("linkedin_profile"));

                    //Instantiate a internship bean
                    Internship internship = new Internship();
                    internship.setInternship(UUID.fromString(rs.getString("internship_id")));
                    internship.setDesciption(rs.getString("description"));
                    internship.setMidInternInfo(rs.getBoolean("mid_intern_info"));
                    internship.setWebSurvey(rs.getBoolean("web_survey"));
                    internship.setBegining(rs.getDate("beginning"));
                    internship.setEnd(rs.getDate("ending"));
                    internship.setCdc(rs.getBoolean("cdc"));
                    internship.setDefense(rs.getBoolean("defense"));
                    internship.setCompanyEval(rs.getBoolean("company_eval"));
                    internship.setInternSupervisor(rs.getString("intern_supervisor"));

                    //Instantiate a company bean
                    Company company = new Company();
                    company.setCompanyId(UUID.fromString(rs.getString("company_id")));
                    company.setName(rs.getString("company_name"));
                    company.setAddress(rs.getString("address"));

                    //Instantiate a visit bean
                    Visit visit = new Visit();
                    visit.setVisitID(UUID.fromString(rs.getString("visit_id")));
                    visit.setDone(rs.getBoolean("done"));
                    visit.setPlanned(rs.getBoolean("planned"));
                    visit.setVisitReport(rs.getBoolean("visit_report"));

                    //Instantiate a marks bean
                    Marks marks = new Marks();
                    marks.setMarksId(UUID.fromString(rs.getString("marks_id")));
                    marks.setCommunication(rs.getInt("communication"));
                    marks.setTech(rs.getInt("tech"));

                    //Instantiate a comments bean
                    Comments comments = new Comments();
                    comments.setCommentsId(UUID.fromString(rs.getString("comments_id")));
                    comments.setStudentComm(rs.getString("student_comm"));
                    comments.setSupervisorComment(rs.getString("supervisor_comm"));


                    //Instantiate a final report bean
                    FinalReport finalReport = new FinalReport();
                    finalReport.setFinalReportId(UUID.fromString(rs.getString("final_report_id")));
                    finalReport.setReport(rs.getBoolean("report"));
                    finalReport.setTitle(rs.getString("title"));

                    //Set attributes of internshipData
                    internshipData.setStudent(student);
                    internshipData.setInternship(internship);
                    internshipData.setCompany(company);
                    internshipData.setVisit(visit);
                    internshipData.setMarks(marks);
                    internshipData.setComments(comments);
                    internshipData.setFinalReport(finalReport);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return internshipData;
    }

    /**
     * Get the list of skills of a student
     * @param studentId, the ID of the student
     * @return the list of skills for the student
     */
    public ArrayList<Skills> getListOfStudentSkills(String studentId){
        listOfSkills = new ArrayList<>();
        rs = dataServices.selectStudentSkillsAll(studentId);
        if (rs != null) {
            try {
                while (rs.next()) {
                    Skills skills = new Skills(rs.getString("skill"), (UUID) rs.getObject("skill_id"));
                    listOfSkills.add(skills);
                }
            } catch (Exception e) {
                Logger.getLogger(Homepage.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return listOfSkills;
    }
}

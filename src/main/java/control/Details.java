package control;

import model.*;
import utils.DataServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import static utils.Constants.*;

@WebServlet(name = "Details")
public class Details extends ServletModel {
    private InternshipData internshipData;
    private ResultSet rs;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String internshipSubmit = request.getParameter("internshipSubmit");
        if (internshipSubmit.equals("details")){
            getInternshipDataDetails(request.getParameter("internshipId"));
            request.getRequestDispatcher(MISSION_PAGE).forward(request, response);
        }else if (internshipSubmit.equals("modify")){

        }else{
            //todo je pense qu'il n'y a plus la liste des internship à renvoyer
            request.getRequestDispatcher(HOME_PAGE).forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    public InternshipData getInternshipDataDetails(String internshipId){
        internshipData = new InternshipData();
        rs = dataServices.selectInternshipDetailed(internshipId);

        if (rs != null){
            try {
                while (rs.next()){
                    Student student = new Student();
                    student.setStudentId(UUID.fromString(rs.getString("StudentId")));
                    student.setName(rs.getString("Name"));
                    student.setFirstName(rs.getString("FirstName"));
                    student.setEmail(rs.getString("Email"));
                    student.setGroup(rs.getString("Group"));
                    student.setLinkedinProfile(rs.getString("LinkedinProfile"));

                    Internship internship = new Internship();
                    internship.setInternship(UUID.fromString(rs.getString("InternshipId")));
                    internship.setDesciption(rs.getString("Description"));
                    internship.setMidInternInfo(rs.getBoolean("MidInternInfo"));
                    internship.setWebSurvey(rs.getBoolean("WebSurvey"));
                    internship.setBegining(rs.getDate("Begining"));
                    internship.setEnd(rs.getDate("End"));

                    Company company = new Company();
                    company.setCompanyId(UUID.fromString(rs.getString("CompanyId")));
                    company.setName(rs.getString("CompanyName"));
                    company.setAddress(rs.getString("Address"));

                    Visit visit = new Visit();
                    visit.setVisitID(UUID.fromString(rs.getString("VisitId")));
                    visit.setDone(rs.getBoolean("Done"));
                    visit.setPlanned(rs.getBoolean("Planned"));
                    visit.setVisitReport(rs.getBoolean("VisitReport"));

                    Marks marks = new Marks();
                    marks.setMarksId(UUID.fromString(rs.getString("MarksId")));
                    marks.setCommunication(rs.getInt("Communication"));
                    marks.setTech(rs.getInt("Tech"));

                    Comments comments = new Comments();
                    comments.setCommentsId(UUID.fromString(rs.getString("CommentsId")));
                    comments.setStudentComm(rs.getString("StudentComm"));
                    comments.setSupervisorComment(rs.getString("SupervisorComm"));

                    internshipData.setStudent(student);
                    internshipData.setInternship(internship);
                    internshipData.setCompany(company);
                    internshipData.setVisit(visit);
                    internshipData.setMarks(marks);
                    internshipData.setComments(comments);

                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return internshipData;
    }
}

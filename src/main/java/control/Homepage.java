package control;

import model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import static utils.Constants.DB_SELECT_SKILLS;
import static utils.Constants.HOME_PAGE;

@WebServlet(name = "Homepage")
public class Homepage extends ServletModel {
    private HttpSession session;
    private ArrayList<Skills> listOfSkills;
    private ArrayList<InternshipData> listOfInternshipdata;
    private Tutor tutor;

    private ResultSet rs;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: Should we keep this post method?
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();
        tutor = (Tutor) session.getAttribute("tutor");
        if (tutor != null) {
            request.setAttribute("listOfSkill", getListOfSkills());
            request.setAttribute("listOfInternship", getListOfInternshipDate(tutor));
            request.getRequestDispatcher(HOME_PAGE).forward(request, response);
        } else {
            response.sendRedirect("Login");
        }
    }

    // TODO: Move this to another location (DateServices or Skills)
    private ArrayList<Skills> getListOfSkills() {
        listOfSkills = new ArrayList<>();
        rs = dataServices.selectResultSet(DB_SELECT_SKILLS);
        if (rs != null) {
            try {
                while (rs.next()) {
                    Skills skills = new Skills(rs.getString("Skill"), (UUID) rs.getObject("SkillId"));
                    listOfSkills.add(skills);
                }
            } catch (Exception e) {
                Logger.getLogger(Homepage.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return listOfSkills;
    }

    //TODO : move
    private ArrayList<InternshipData> getListOfInternshipDate(Tutor tutor) {
        listOfInternshipdata = new ArrayList<>();
        rs = dataServices.selectInternships(tutor.getTutorId().toString());

        if (rs != null) {
            try {
                while (rs.next()) {
                    Student student = new Student();
                    student.setStudentId(UUID.fromString(rs.getString("StudentId")));
                    student.setName(rs.getString("Name"));
                    student.setFirstName(rs.getString("FirstName"));
                    student.setEmail(rs.getString("Email"));
                    student.setGroup(rs.getString("Group"));

                    Internship internship = new Internship();
                    internship.setInternship(UUID.fromString(rs.getString("InternshipId")));
                    internship.setDesciption(rs.getString("Description"));
                    internship.setMidInternInfo(rs.getBoolean("MidInternInfo"));
                    internship.setWebSurvey(rs.getBoolean("WebSurvey"));
                    internship.setBegining(rs.getDate("Beginning"));
                    internship.setEnd(rs.getDate("End"));
                    internship.setCdc(rs.getBoolean("Cdc"));
                    internship.setDefense(rs.getBoolean("Defense"));
                    internship.setCompanyEval(rs.getBoolean("CompanyEval"));
                    internship.setInternSupervisor(rs.getString("InternSupervisor"));

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

                    InternshipData internshipData = new InternshipData();
                    internship.setInternship(UUID.fromString(rs.getString("InternshipId")));
                    internshipData.setStudent(student);
                    internshipData.setInternship(internship);
                    internshipData.setCompany(company);
                    internshipData.setVisit(visit);
                    internshipData.setMarks(marks);

                    listOfInternshipdata.add(internshipData);
                }
            } catch (Exception e) {
                Logger.getLogger(Homepage.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        return listOfInternshipdata;
    }
}

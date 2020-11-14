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

import static utils.Constants.*;

@WebServlet(name = "Homepage")
public class Homepage extends ServletModel {
    private HttpSession session;
    private ArrayList<Skills> listOfSkills;
    private ArrayList<Keywords> listOfKeywords;
    private ArrayList<InternshipData> listOfInternshipdata;

    private Tutor tutor;
    private int year;
    private String name;
    String keyword;


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
            request.setAttribute("listOfKeywords", getListOfKeywords());

            try {
                year = Integer.parseInt(request.getParameter("year"));
            } catch (NumberFormatException e) {
                year= 2020;
            }
            name = request.getParameter("search-name");
            if(name == null) {
                name = "";
            }
            keyword = request.getParameter("keywords");
            if(keyword == null) {
                keyword = "%";
            }
            request.setAttribute("listOfInternship", getListOfInternshipDate(tutor));

            request.getRequestDispatcher(HOME_PAGE).forward(request, response);
        } else {
            response.sendRedirect("Login");
        }
    }

    // TODO: Move this or DELETE IT
    private ArrayList<Skills> getListOfSkills() {
        listOfSkills = new ArrayList<>();
        rs = dataServices.selectResultSet(DB_SELECT_SKILLS);
        if (rs != null) {
            try {
                while (rs.next()) {
                    Skills skills = new Skills(rs.getString("Skill"), (UUID) rs.getObject("Skill_Id"));
                    listOfSkills.add(skills);
                }
            } catch (Exception e) {
                Logger.getLogger(Homepage.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return listOfSkills;
    }

    // TODO: Move this to another location (DateServices or Skills)
    private ArrayList<Keywords> getListOfKeywords() {
        listOfKeywords = new ArrayList<>();
        rs = dataServices.selectResultSet(DB_SELECT_KEYWORDS);
        if (rs != null) {
            try {
                while (rs.next()) {
                    Keywords keywords = new Keywords(rs.getString("keyword"), (UUID) rs.getObject("keyword_Id"));
                    listOfKeywords.add(keywords);
                }
            } catch (Exception e) {
                Logger.getLogger(Homepage.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return listOfKeywords;
    }

    //TODO : move
    private ArrayList<InternshipData> getListOfInternshipDate(Tutor tutor) {
        listOfInternshipdata = new ArrayList<>();
        rs = dataServices.selectInternships(tutor.getTutorId().toString(), year, name, keyword);

        if (rs != null) {
            try {
                while (rs.next()) {
                    Student student = new Student();
                    student.setStudentId(UUID.fromString(rs.getString("student_id")));
                    student.setName(rs.getString("name"));
                    student.setFirstName(rs.getString("firstname"));
                    student.setEmail(rs.getString("email"));
                    student.setGroup(rs.getString("group"));
                    student.setLinkedinProfile(rs.getString("linkedin_profile"));

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

                    Company company = new Company();
                    company.setCompanyId(UUID.fromString(rs.getString("company_id")));
                    company.setName(rs.getString("company_name"));
                    company.setAddress(rs.getString("address"));

                    Visit visit = new Visit();
                    visit.setVisitID(UUID.fromString(rs.getString("visit_id")));
                    visit.setDone(rs.getBoolean("done"));
                    visit.setPlanned(rs.getBoolean("planned"));
                    visit.setVisitReport(rs.getBoolean("visit_report"));

                    Marks marks = new Marks();
                    marks.setMarksId(UUID.fromString(rs.getString("marks_id")));
                    marks.setCommunication(rs.getInt("communication"));
                    marks.setTech(rs.getInt("tech"));

                    InternshipData internshipData = new InternshipData();
                    internship.setInternship(UUID.fromString(rs.getString("internship_id")));
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

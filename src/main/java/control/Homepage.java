package control;

import model.*;
import utils.database.InternshipDataServices;

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

/**
 * Homepage controller, displaying list of internships
 */
@WebServlet(name = "Homepage")
public class Homepage extends ServletModel {
    private HttpSession session;
    private ArrayList<Skills> listOfSkills;
    private ArrayList<Keywords> listOfKeywords;

    private InternshipDataServices internshipDataServices;

    private Tutor tutor;
    private int year;
    private String name;
    String keyword;

    // Todo: Remove this
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
            // TODO: Remove this :)
            //request.setAttribute("listOfKeywords", getListOfKeywords());

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
            request.setAttribute("listOfInternship", internshipDataServices.getListInternships(tutor,year, name, keyword));

            request.getRequestDispatcher(HOME_PAGE).forward(request, response);
        } else {
            response.sendRedirect("Login");
        }
    }

    @Override
    public void init() {
        super.init();
        internshipDataServices = new InternshipDataServices(dbUser, dbPwd, dbUrl);
    }

    // TODO: Will be moved later, don't touch this
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

    // TODO: Will be moved later, don't touch this
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

}

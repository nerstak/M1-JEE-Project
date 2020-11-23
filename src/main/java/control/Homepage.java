package control;

import model.Keywords;
import model.Skills;
import model.Tutor;
import utils.database.InternshipDataServices;
import utils.database.KeywordsDataServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

import static utils.Constants.HOME_PAGE;

/**
 * Homepage controller, displaying list of internships
 */
@WebServlet(name = "Homepage")
public class Homepage extends ServletModel {
    private HttpSession session;

    private InternshipDataServices internshipDataServices;
    private KeywordsDataServices keywordsDataServices;

    private Tutor tutor;
    private int year;
    private String name;
    String keyword;

    @Override
    public void init() {
        super.init();
        internshipDataServices = new InternshipDataServices(dbUser, dbPwd, dbUrl);
        keywordsDataServices = new KeywordsDataServices(dbUser, dbPwd, dbUrl);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();
        tutor = (Tutor) session.getAttribute("tutor");
        if (tutor != null) {
            request.setAttribute("listOfKeywords", keywordsDataServices.getListOfKeywords());

            try {
                year = Integer.parseInt(request.getParameter("year"));
            } catch (NumberFormatException e) {
                year = 2020;
            }
            name = request.getParameter("search-name");
            if (name == null) {
                name = "";
            }

            keyword = request.getParameter("keywords");
            if (keyword == null) {
                keyword = "%";
            }

            request.setAttribute("listOfInternship", internshipDataServices.getListInternships(tutor, year, name, keyword));
            request.setAttribute("searchedYear", year);
            request.setAttribute("searchedKeyword", keyword);
            request.setAttribute("searchedName", name);

            request.getRequestDispatcher(HOME_PAGE).forward(request, response);
        } else {
            response.sendRedirect("Login");
        }
    }
}

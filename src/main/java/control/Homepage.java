package control;

import control.sessionBeans.InternshipSessionBean;
import control.sessionBeans.KeywordsSessionBean;
import modelsEntities.TutorEntity;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static utils.Constants.HOME_PAGE;

/**
 * Homepage controller, displaying list of internships
 */
@WebServlet(name = "Homepage")
public class Homepage extends ServletModel {
    private HttpSession session;

    @EJB
    private KeywordsSessionBean keywordsSB;
    @EJB
    private InternshipSessionBean internshipsSB;

    private TutorEntity tutor;
    private int year;
    private String name;
    String keyword;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();
        tutor = (TutorEntity) session.getAttribute("tutor");
        if (tutor != null) {
            request.setAttribute("listOfKeywords", keywordsSB.getKeywords());

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
            if(keyword == null) {
                keyword = "-";
            }

            request.setAttribute("listOfInternship",internshipsSB.getInternshipData(tutor.getTutorId(), year, name, keyword));
            request.setAttribute("searchedYear", year);
            request.setAttribute("searchedKeyword", keyword);
            request.setAttribute("searchedName", name);

            request.getRequestDispatcher(HOME_PAGE).forward(request, response);
        } else {
            response.sendRedirect("Login");
        }
    }
}

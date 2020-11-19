package control;

import model.Tutor;
import utils.database.TutorDataServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static utils.Constants.*;

/**
 * Login controller, to handle connection
 */
@WebServlet(name = "Login")
public class Login extends ServletModel {
    private HttpSession session;

    private TutorDataServices tutorDataServices;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();

        if(session.getAttribute("tutor") != null) {
            response.sendRedirect("Homepage");
        } else {
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Only for post request
        session = request.getSession();

        if(session.getAttribute("tutor") != null) {
            response.sendRedirect("Homepage");
        }

        Tutor tutor = new Tutor();
        tutor.setEmail(request.getParameter("login"));
        tutor.setPwd(request.getParameter("pwd"));

        if (tutor.getEmail().isEmpty() || tutor.getPwd().isEmpty()) {
            request.setAttribute("errorMessage", ERR_MISSING_FIELD);
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response); //redirect to welcome if ok
        }

        if (tutorDataServices.selectTutor(tutor)) {
            session.setAttribute("tutor", tutor);
            response.sendRedirect("Homepage");
        } else {
            request.setAttribute("errorMessage", ERR_INV_CRED_MESS);
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        }
    }

    @Override
    public void init() {
        super.init();
        tutorDataServices = new TutorDataServices(dbUser, dbPwd, dbUrl);
    }
}

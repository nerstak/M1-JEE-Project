package control;

import model.Tutor;
import utils.database.TutorData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import static utils.Constants.*;

/**
 * Login controller, to handle connection
 */
@WebServlet(name = "Login")
public class Login extends ServletModel {
    private HttpSession session;

    private TutorData tutorData;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Only for post request
        Tutor tutor = new Tutor();
        tutor.setEmail(request.getParameter("login"));
        tutor.setPwd(request.getParameter("pwd"));

        if (tutor.getEmail().isEmpty() || tutor.getPwd().isEmpty()) {
            request.setAttribute("errorMessage", ERR_MISSING_FIELD);
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response); //redirect to welcome if ok
        }

        if (tutorData.selectTutor(tutor)) {
            session = request.getSession();
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
        tutorData = new TutorData(dbUser, dbPwd, dbUrl);
    }
}

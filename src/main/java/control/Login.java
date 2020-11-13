package control;

import model.*;
import utils.DataServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import static utils.Constants.*;

@WebServlet(name = "Login")
public class Login extends ServletModel {
    private HttpSession session;

    private ResultSet rs;

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

        if (checkCredentials(tutor)) {
            session = request.getSession();
            session.setAttribute("tutor", tutor);
            response.sendRedirect("Homepage");
        } else {
            request.setAttribute("errorMessage", ERR_INV_CRED_MESS);
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        }
    }

    /**
     * Check login credentials from database
     *
     * @param myTutor user with his login/pwd
     * @return true/false connection
     */
    private boolean checkCredentials(Tutor myTutor) {
        rs = dataServices.selectTutor(myTutor.getEmail(),myTutor.getPwd());
        if (rs != null) {
            try {
                if (rs.next()) { //if rs contain the user data => set bean's property
                    myTutor.setTutorId(UUID.fromString(rs.getString("TutorId")));
                    myTutor.setFirstName(rs.getString("FirstName"));
                    myTutor.setName(rs.getString("Name"));
                    return true;
                } else { //no data returned = error in login or password
                    return false;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }
}

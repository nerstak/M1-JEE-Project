package control;

import model.Tutor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import static utils.Constants.*;

@WebServlet(name = "Controller")
public class Controller extends HttpServlet {
    private Properties properties;
    private InputStream input;

    private String dbUrl;
    private String dbUser;
    private String dbPwd;

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private PreparedStatement ps;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("login") == null) {
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        } else {
            Tutor tutor = new Tutor();
            tutor.setEmail(request.getParameter("login"));
            tutor.setPwd(request.getParameter("pwd"));

            if(checkCredentials(tutor)) {
                request.getRequestDispatcher(HOMEPAGE_PAGE).forward(request, response);
            }
        }
    }

    /**
     * Read properties file
     * @return Properties
     */
    private Properties getPropertiesFile() {
        try {
            properties = new Properties();
            input = getServletContext().getResourceAsStream("/WEB-INF/db.properties");
            properties.load(input);
        } catch (IOException e) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, e);
        }

        return properties;
    }

    @Override
    public void init() {
        getPropertiesFile();

        dbUrl = properties.getProperty("DB.URL");
        dbUser = properties.getProperty("DB.USER");
        dbPwd = properties.getProperty("DB.PWD");

        try {
            conn = DriverManager.getConnection(dbUrl,dbUser,dbPwd);
            stmt = conn.createStatement();
        } catch (SQLException e) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private boolean checkCredentials(Tutor tutor) {
        try {
            ps = conn.prepareStatement(TUTOR_PASSWORD);
            ps.setString(1,tutor.getEmail());
            rs = ps.executeQuery();

            if(rs.next()) {
                if(tutor.getPwd().equals(rs.getString("Pwd"))) {
                    return true;
                }
            }
        } catch (SQLException throwables) {
            return false;
        }
        return false;
    }
}

package control;

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

import static utils.Constants.LOGIN_PAGE;

@WebServlet(name = "Controller")
public class Controller extends HttpServlet {
    private Properties properties;
    private InputStream input;

    private String dbUrl;
    private String dbUser;
    private String dbPwd;

    private Connection conn;
    private Statement stmt;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Quick and dirty connection test
        /*
        try {
            ResultSet rs = stmt.executeQuery("SELECT first_name FROM supervisor WHERE id = 1");
            if(rs.next()) {
                request.setAttribute("name", rs.getString("first_name"));
            }
        } catch (SQLException e) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, e);
        }*/

        request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
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

        /*try {
            conn = DriverManager.getConnection(dbUrl,dbUser,dbPwd);
            stmt = conn.createStatement();
        } catch (SQLException e) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, e);
        }*/
    }
}

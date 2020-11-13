package control;

import utils.DataServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import static utils.Constants.DB_PROPERTIES;

@WebServlet(name = "ServletModel")
public abstract class ServletModel extends HttpServlet {
    protected Properties properties;
    protected DataServices dataServices;
    protected InputStream input;

    private String dbUrl;
    private String dbUser;
    private String dbPwd;

    private Connection conn;
    private Statement stmt;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void init() {
        getPropertiesFile();

        dbUrl = properties.getProperty("DB.URL");
        dbUser = properties.getProperty("DB.USER");
        dbPwd = properties.getProperty("DB.PWD");

        try {
            Class.forName(properties.getProperty("DB.JDBC"));
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
            stmt = conn.createStatement();
            dataServices = new DataServices(dbUser, dbPwd, dbUrl);
        } catch (SQLException e) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the db.properties file
     *
     * @return Properties from the db.properties
     */
    private Properties getPropertiesFile() {
        properties = new Properties();
        try {
            input = getServletContext().getResourceAsStream(DB_PROPERTIES);
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}

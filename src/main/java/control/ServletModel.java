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

/**
 * Basis of any Controller, as it creates the connection with the database
 */
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

    @Override
    public void init() {
        getPropertiesFile();

        dbUrl = properties.getProperty("DB.URL");
        dbUser = properties.getProperty("DB.USER");
        dbPwd = properties.getProperty("DB.PWD");

        try {
            // Setting up database connection
            Class.forName(properties.getProperty("DB.JDBC"));
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
            stmt = conn.createStatement();
            // May have to be removed later
            dataServices = new DataServices(dbUser, dbPwd, dbUrl);
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(ServletModel.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Load the db.properties file
     */
    private void getPropertiesFile() {
        properties = new Properties();
        try {
            input = getServletContext().getResourceAsStream(DB_PROPERTIES);
            properties.load(input);
        } catch (IOException e) {
            Logger.getLogger(ServletModel.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}

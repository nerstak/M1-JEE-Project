package control;

import model.Tutor;
import utils.DataServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import static utils.Constants.*;

@WebServlet(name = "Controller")
public class Controller extends HttpServlet {
    private Properties properties;
    DataServices dataServices;
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

            if (tutor.getEmail().isEmpty() || tutor.getPwd().isEmpty()){
                request.getRequestDispatcher(LOGIN_PAGE).forward(request, response); //redirect to welcome if ok
                request.setAttribute("errorMessage", ERR_MISSING_FIELD);
            }

            if(checkCredentials(tutor)) {
                request.getRequestDispatcher(HOME_PAGE).forward(request, response);
            } else {
                request.setAttribute("errorMessage",ERR_INV_CRED_MESS);
                request.getRequestDispatcher(LOGIN_PAGE).forward(request,response);
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
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

            dataServices = new DataServices(dbUser,dbPwd,dbUrl);
        } catch (SQLException e) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * todo change to handle just the good one
     * Use to handle both get and post request
     * @param req
     * @param resp
     */
    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Use to redirect when controller is the entry point
        if(req.getParameter("login") == null){
            req.getRequestDispatcher(LOGIN_PAGE).forward(req, resp); //redirect to welcome if ok
        }else{
            //Check if parameter(s) are null
            String login = req.getParameter("login");
            String pwd = req.getParameter("pwd");
            if (login.isEmpty() || pwd.isEmpty()){
                req.getRequestDispatcher(LOGIN_PAGE).forward(req, resp); //redirect to welcome if ok
                req.setAttribute("errorMessage", "Empty field(s)");
            }

            //Get properties
            properties = getPropertiesFile();

            //Instantiate Tutor
            Tutor myTutor = new Tutor();
            myTutor.setName(login);
            myTutor.setPwd(pwd);

            //check credentials and redirect
            if (checkCredentials(myTutor)) {
                req.getRequestDispatcher(HOME_PAGE).forward(req, resp);
            } else {
                req.setAttribute("errorMessage", ERR_INV_CRED_MESS);
                req.getRequestDispatcher(LOGIN_PAGE).forward(req, resp);
            }
        }
    }

    /**
     * Check login credentials from database
     * @param myTutor user with his login/pwd
     * @return true/false connection
     */
    private boolean checkCredentials(Tutor myTutor){
        // Todo Convert this simple Statement to a Prepared Statement
        // See: https://github.com/nerstak/M1-JEE-Project/blob/feature/linking-db-and-pages/src/main/java/control/Controller.java#L80
        rs = dataServices.selectResultSet("SELECT * FROM \"Tutor\" WHERE \"Email\"='" + myTutor.getEmail() + "' AND \"Pwd\"='" + myTutor.getPwd() + "';");
        if (rs != null){
            try {
                if(rs.next()){ //if rs contain the user data => set bean's property
                    myTutor.setTutorId(UUID.fromString(rs.getString("TutorId")));
                    myTutor.setFirstName(rs.getString("FirstName"));
                    myTutor.setName(rs.getString("Name"));
                    return true;
                }
                else { //no data returned = error in login or password
                    return false;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return false;
            }
        }else{
            return false;
        }

    }

    /**
     * Get the db.properties file
     * @return Properties from the db.properties
     */
    private Properties getPropertiesFile(){
        properties = new Properties();
        try{
            input = getServletContext().getResourceAsStream(DB_PROPERTIES);
            properties.load(input);
        }
        catch (IOException e ){
            e.printStackTrace();
        }
        return properties;
    }
/**
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
 **/
}

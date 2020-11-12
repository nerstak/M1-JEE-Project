package control;

import model.Skills;
import model.Tutor;
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

@WebServlet(name = "Controller")
public class Controller extends HttpServlet {
    private Properties properties;
    DataServices dataServices;
    private InputStream input;

    private String dbUrl;
    private String dbUser;
    private String dbPwd;

    private HttpSession session;

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private PreparedStatement ps;

    private ArrayList<Skills> listOfSkills;

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
            session.setAttribute("listOfSkill", getListOfSkills());
            // TODO: Link SELECT Internships
            request.getRequestDispatcher(HOME_PAGE).forward(request, response);
        } else {
            request.setAttribute("errorMessage", ERR_INV_CRED_MESS);
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        }
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
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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

    // TODO: Move this to another location (DateServices or Skills)
    private ArrayList<Skills> getListOfSkills(){
        listOfSkills = new ArrayList<>();
        rs = dataServices.selectResultSet(DB_SELECT_SKILLS);
        if (rs != null){
            try {
                while (rs.next()){
                    Skills skills = new Skills(rs.getString("Skill"), (UUID) rs.getObject("SkillId"));
                    listOfSkills.add(skills);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return listOfSkills;
    }
}

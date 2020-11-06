package control;

import model.Tutor;
import utils.DataServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.UUID;

import static utils.Constants.*;
import static utils.Constants.HOME_PAGE;
import static utils.Constants.LOGIN_PAGE;

@WebServlet(name = "Controller")
public class Controller extends HttpServlet {
    private Properties properties;
    private InputStream input;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);


        /* OLD ONE
        // Quick and dirty connection test
        try {
            ResultSet rs = stmt.executeQuery("SELECT first_name FROM supervisor WHERE id = 1");
            if(rs.next()) {
                request.setAttribute("name", rs.getString("first_name"));
            }
        } catch (SQLException e) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, e);
        }

        request.getRequestDispatcher(WELCOME_PAGE).forward(request, response);*/
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
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
            }

            //Get properties
            properties = getPropertiesFile();

            //Instantiate Tutor
            Tutor myTutor = new Tutor();
            myTutor.setName(login);
            myTutor.setPwd(pwd);

            //check credentials and redirect
            if (checkCredentials(myTutor)) {
                req.getRequestDispatcher(HOME_PAGE).forward(req, resp); //redirect to welcome if ok
            } else {
                req.setAttribute("errorMessage", ERR_MSG);
                req.getRequestDispatcher(LOGIN_PAGE).forward(req, resp); //redirect to index if not
            }
        }
    }

    /**
     * Check login credentials from database
     * @param myTutor user with his login/pwd
     * @return true/false connection
     */
    private boolean checkCredentials(Tutor myTutor){
        DataServices dataServices = new DataServices(properties.getProperty("DB.USER"), properties.getProperty("DB.PWD"),properties.getProperty("DB.URL") , properties.getProperty("DB.JDBC"));
        ResultSet rs = dataServices.selectResultSet("SELECT * FROM \"Tutor\" WHERE \"Name\"='" + myTutor.getName() + "' AND \"Pwd\"='" + myTutor.getPwd() + "';");
        if (rs != null){
            try {
                if(rs.next()){ //if rs contain the user data => set bean's property
                    myTutor.setTutorId(UUID.fromString(rs.getString("TutorId")));
                    myTutor.setFirstName(rs.getString("FirstName"));
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

    private DataServices connectDb(String dbLogin, String dbpwd, String dbUrl){
        return new DataServices(dbUrl, dbLogin, dbpwd, properties.getProperty("DB.JDBC"));
    }

    /**
     * Get the db.properties file
     * @return Properties from the db.properties
     */
    private Properties getPropertiesFile(){
        Properties prop = new Properties();
        try{
            InputStream stream = getServletContext().getResourceAsStream("/WEB-INF/db.properties");
            prop.load(stream);
        }
        catch (IOException e ){
            e.printStackTrace();
        }
        return prop;
    }
}

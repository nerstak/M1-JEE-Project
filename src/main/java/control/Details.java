package control;

import model.*;
import utils.database.InternshipDataServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import static utils.Constants.*;

/**
 * Details controller, for any additional information on internship
 */
@WebServlet(name = "Details")
public class Details extends ServletModel {
    private InternshipData internshipData;
    private ResultSet rs;
    private ArrayList<Skills> listOfSkills;

    private InternshipDataServices internshipDataServices;

    @Override
    public void init() {
        super.init();
        internshipDataServices = new InternshipDataServices(dbUser, dbPwd, dbUrl);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Check from which submit button the request come from
        String internshipSubmit = request.getParameter("internshipSubmit");
        if (internshipSubmit.equals("details")){
            String internshipId = request.getParameter("internshipId");
            internshipData = internshipDataServices.getInternshipDetailed(internshipId);

            //Set request attributes
            request.setAttribute("internshipData", internshipData);
            //request.setAttribute("listOfStudentSkills", getListOfStudentSkills(internshipData.getStudent().getStudentId().toString()));
            request.getRequestDispatcher(MISSION_PAGE).forward(request, response);
        }else if (internshipSubmit.equals("modify")){

        }else{
            //todo je pense qu'il n'y a plus la liste des internship à renvoyer
            request.getRequestDispatcher(HOME_PAGE).forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

    // TODO: Will be moved later, don't touch this
    /**
     * Get the list of skills of a student
     * @param studentId, the ID of the student
     * @return the list of skills for the student
     */
    public ArrayList<Skills> getListOfStudentSkills(String studentId){
        listOfSkills = new ArrayList<>();
        rs = dataServices.selectStudentSkillsAll(studentId);
        if (rs != null) {
            try {
                while (rs.next()) {
                    Skills skills = new Skills(rs.getString("skill"), (UUID) rs.getObject("skill_id"));
                    listOfSkills.add(skills);
                }
            } catch (Exception e) {
                Logger.getLogger(Homepage.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return listOfSkills;
    }
}

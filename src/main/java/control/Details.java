package control;

import model.*;
import utils.database.InternshipDataServices;
import utils.database.SkillsDataServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static utils.Constants.*;

/**
 * Details controller, for any additional information on internship
 */
@WebServlet(name = "Details")
public class Details extends ServletModel {
    private InternshipData internshipData;

    private InternshipDataServices internshipDataServices;
    private SkillsDataServices skillsDataServices;

    @Override
    public void init() {
        super.init();
        internshipDataServices = new InternshipDataServices(dbUser, dbPwd, dbUrl);
        skillsDataServices = new SkillsDataServices(dbUser, dbPwd, dbUrl);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Check from which submit button the request come from
        String internshipSubmit = request.getParameter("internshipSubmit");
        if (internshipSubmit.equals("details")) {
            String internshipId = request.getParameter("internshipId");
            internshipData = internshipDataServices.getInternshipDetailed(internshipId);

            //Set request attributes
            request.setAttribute("internshipData", internshipData);
            request.setAttribute("listOfStudentSkills",
                    skillsDataServices.getStudentSkillsAll(internshipData.getStudent()));
            request.getRequestDispatcher(MISSION_PAGE).forward(request, response);
        } else if (internshipSubmit.equals("modify")) {

        } else {
            response.sendRedirect("Homepage");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("Homepage");
    }
}

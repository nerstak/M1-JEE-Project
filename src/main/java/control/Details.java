package control;

import model.InternshipData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static utils.Constants.HOME_PAGE;

@WebServlet(name = "Details")
public class Details extends HttpServlet {
    InternshipData internshipData;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String internshipSubmit = request.getParameter("internshipSubmit");
        if (internshipSubmit.equals("details")){
            System.out.print("Details");
        }else if (internshipSubmit.equals("modify")){
            System.out.print("Modify");
        }else{
            //todo je pense qu'il n'y a plus la liste des internship à renvoyer
            request.getRequestDispatcher(HOME_PAGE).forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }



}

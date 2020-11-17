package control;


import model.Student;
import utils.database.InternshipDataServices;
import utils.database.StudentDataServices;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class UpdateDetails extends ServletModel{


    @Override
    public void init() {
        super.init();
        InternshipDataServices internshipDataServices = new InternshipDataServices(dbUser, dbPwd, dbUrl);
        StudentDataServices studentDataServices = new StudentDataServices(dbUser, dbPwd, dbUrl);
        InternshipDataServices internshipDataServices = new InternshipDataServices(dbUser, dbPwd, dbUrl);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String detailsSubmitButton = request.getParameter("updateDetails");
        switch (detailsSubmitButton){
            case "company":
                updateCompany(request);
                break;
            case "student":
                updateStudent(request);
                break;
            case "internship":
                updateInternship(request);
                break;
            case "keywords":
//                TODO to be done
                break;
            case "skills":
//                TODO to be done
                break;
            default:
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }


    private void updateCompany(HttpServletRequest request){
        String companyName = request.getParameter("companyName");
        String mds = request.getParameter("mds");
        String begin =  request.getParameter("begin");
        String end = request.getParameter("end");
        String companyAddress = request.getParameter("companyAddress");

    }

    private void updateStudent(HttpServletRequest request){
        String studentId = request.getParameter("studentId");
        String group = request.getParameter("group");
        String firstName =  request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String linkedin = request.getParameter("linkedin");
    }

    private void updateInternship(HttpServletRequest request){
        String description = request.getParameter("description");
        String tutorComments = request.getParameter("tutorComments");
        String studentComments = request.getParameter("studentComments");

    }

    private void updateSkills(HttpServletRequest request){

    }

    private void updateKeywords(HttpServletRequest request){

    }

}

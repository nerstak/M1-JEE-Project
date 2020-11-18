package control;


import model.InternshipData;
import model.Student;
import utils.database.InternshipDataServices;
import utils.database.StudentDataServices;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import static utils.Constants.*;

public class UpdateDetails extends ServletModel{
    private InternshipDataServices internshipDataServices;
    private StudentDataServices studentDataServices;

    @Override
    public void init() {
        super.init();
        internshipDataServices = new InternshipDataServices(dbUser, dbPwd, dbUrl);
        studentDataServices = new StudentDataServices(dbUser, dbPwd, dbUrl);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String detailsSubmitButton = request.getParameter("updateDetails");
        String internshipId = request.getParameter("internshipId");
        switch (detailsSubmitButton){
            case "company":
                updateCompany(request);
                break;
            case "student":
                boolean success = updateStudent(request);
                if (success){
                    request.setAttribute("message", SUCCESS_BD);
                }else{
                    request.setAttribute("message", ERR_FAILED_UPDATE_DB);
                }
                request.setAttribute("internshipData", internshipDataServices.getInternshipDetailed(internshipId));
                request.getRequestDispatcher(MISSION_PAGE).forward(request,response);
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

    private boolean updateStudent(HttpServletRequest request){
        UUID studentId = UUID.fromString(request.getParameter("studentId"));
        String group = request.getParameter("group");
        String firstName =  request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String linkedin = request.getParameter("linkedin");
        String email = request.getParameter("email");
        Student student = new Student();
        student.setStudentId(studentId);
        student.setGroup(group);
        student.setLinkedinProfile(linkedin);
        student.setFirstName(firstName);
        student.setName(lastName);
        student.setEmail(email);

        return (studentDataServices.updateStudent(student) == 1);
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

    private InternshipData getInternshipDataDetails(String internshipId){
        return internshipDataServices.getInternshipDetailed(internshipId);
    }
}

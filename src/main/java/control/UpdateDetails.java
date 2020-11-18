package control;


import model.InternshipData;
import model.Student;
import utils.database.CompanyDataServices;
import utils.database.FinalReportDataServices;
import utils.database.InternshipDataServices;
import utils.database.StudentDataServices;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.UUID;

import static utils.Constants.*;

public class UpdateDetails extends ServletModel{
    private InternshipDataServices internshipDataServices;
    private StudentDataServices studentDataServices;
    private CompanyDataServices companyDataServices;
    private FinalReportDataServices finalReportDataServices;
    private boolean successRequest;

    @Override
    public void init() {
        super.init();
        internshipDataServices = new InternshipDataServices(dbUser, dbPwd, dbUrl);
        studentDataServices = new StudentDataServices(dbUser, dbPwd, dbUrl);
        finalReportDataServices = new FinalReportDataServices(dbUser, dbPwd, dbUrl);
        companyDataServices = new CompanyDataServices(dbUser, dbPwd, dbUrl);

    }

    //TODO validation des données reçues des formulaires

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String detailsSubmitButton = request.getParameter("updateDetails");
        String internshipId = request.getParameter("internshipId");
        switch (detailsSubmitButton){
            case "company":
                successRequest = updateCompany(request);
                if (successRequest){
                    request.setAttribute("message", SUCCESS_BD);
                }else{
                    request.setAttribute("message", ERR_FAILED_UPDATE_DB);
                }
                request.setAttribute("internshipData", internshipDataServices.getInternshipDetailed(internshipId));
                request.getRequestDispatcher(MISSION_PAGE).forward(request,response);
                break;
            case "student":
                successRequest = updateStudent(request);
                if (successRequest){
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


    private boolean updateCompany(HttpServletRequest request){
        //Get data from form
        String companyName = request.getParameter("companyName");
        String companyId = request.getParameter("companyId");
        String companyAddress = request.getParameter("companyAddress");

        String internshipId = request.getParameter("internshipId");
        String begin = request.getParameter("begin");
        String end = request.getParameter("end");
        String mds = request.getParameter("mds");

        //Do the request

        internshipDataServices.disableAutoCommit();
        companyDataServices.disableAutoCommit();
        int rowAffectedInternship = internshipDataServices.updateInternshipFromCompanyDetailsPage(internshipId, Date.valueOf(begin), Date.valueOf(end), mds);
        int rowAffectedCompany = companyDataServices.updateCompany(companyId, companyName, companyAddress);

        if ((rowAffectedCompany == 1) && (rowAffectedInternship == 1)){
            internshipDataServices.commitRequest();
            companyDataServices.commitRequest();
            return true;
        }else{
            return false;
        }
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
        String commentsId = request.getParameter("commentsId");
        String internshipId = request.getParameter("internshipId");
        String titleId = request.getParameter("titleId");
        String title = request.getParameter("reportTitle");

        internshipDataServices.updateInternshipDescription(internshipId, description);

        finalReportDataServices.updateTitleReport(titleId, title);




    }

    private void updateSkills(HttpServletRequest request){

    }

    private void updateKeywords(HttpServletRequest request){

    }

    private InternshipData getInternshipDataDetails(String internshipId){
        return internshipDataServices.getInternshipDetailed(internshipId);
    }
}

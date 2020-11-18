package control;


import model.InternshipData;
import model.Student;
import utils.database.*;

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
    private CommentsDataServices commentsDataServices;
    private boolean successRequest;

    @Override
    public void init() {
        super.init();
        internshipDataServices = new InternshipDataServices(dbUser, dbPwd, dbUrl);
        studentDataServices = new StudentDataServices(dbUser, dbPwd, dbUrl);
        finalReportDataServices = new FinalReportDataServices(dbUser, dbPwd, dbUrl);
        companyDataServices = new CompanyDataServices(dbUser, dbPwd, dbUrl);
        commentsDataServices = new CommentsDataServices(dbUser, dbPwd, dbUrl);
    }

    //TODO validation des données reçues des formulaires

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Get the name of the button that call the servlet
        String detailsSubmitButton = request.getParameter("updateDetails");
        String internshipId = request.getParameter("internshipId");
        switch (detailsSubmitButton){
            case "company":
                successRequest = updateCompany(request);
                break;
            case "student":
                successRequest = updateStudent(request);
                break;
            case "internship":
                successRequest = updateInternship(request);
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
        redirectToDetailsPage(request, response, internshipId);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    /**
     * Get the information from the form and update the company and internship tables
     * @param request, servlet request
     * @return true if the database has been updated
     */
    private boolean updateCompany(HttpServletRequest request){
        //Get data from form
            //Company
        String companyName = request.getParameter("companyName");
        String companyId = request.getParameter("companyId");
        String companyAddress = request.getParameter("companyAddress");
            //Internship
        String internshipId = request.getParameter("internshipId");
        String begin = request.getParameter("begin");
        String end = request.getParameter("end");
        String mds = request.getParameter("mds");

        //Disable the autocommit of the dataservices in case of error
        disableAutoCommits(internshipDataServices, companyDataServices);
        int rowAffectedInternship = internshipDataServices.updateInternshipFromCompanyDetailsPage(internshipId, Date.valueOf(begin), Date.valueOf(end), mds);
        int rowAffectedCompany = companyDataServices.updateCompany(companyId, companyName, companyAddress);

        if ((rowAffectedCompany == 1) && (rowAffectedInternship == 1)){ //if all the data has been updates => commit the request
            commitRequest(internshipDataServices, companyDataServices);
            return true;
        }else{ //rollback
            return false;
        }
    }

    /**
     * Get the information from the form and update the student table
     * @param request, servlet request
     * @return true if the database has been updated
     */
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

    /**
     * Get the information from the form and update the student, comments and internship table
     * @param request, servlet request
     * @return true if the database has been updated
     */
    private boolean updateInternship(HttpServletRequest request){
        String description = request.getParameter("description");
        String tutorComments = request.getParameter("tutorComments");
        String studentComments = request.getParameter("studentComments");
        String commentsId = request.getParameter("commentsId");
        String internshipId = request.getParameter("internshipId");
        String titleId = request.getParameter("titleId");
        String title = request.getParameter("reportTitle");

        disableAutoCommits(internshipDataServices, finalReportDataServices, commentsDataServices);

        int rowAffectedInternship = internshipDataServices.updateInternshipDescription(internshipId, description);
        int rowAffectedFinalReport = finalReportDataServices.updateTitleReport(titleId, title);
        int rowAffectedComments = commentsDataServices.updateComments(commentsId, studentComments, tutorComments);

        if (((rowAffectedFinalReport == 1) && (rowAffectedInternship == 1)) && (rowAffectedComments == 1)){
            commitRequest(internshipDataServices, finalReportDataServices, commentsDataServices);
            return true;
        }else{
            return false;
        }
    }

    private void updateSkills(HttpServletRequest request){

    }

    private void updateKeywords(HttpServletRequest request){

    }

    /**
     * Redirect to details jsp
     * @param request, the request
     * @param response, response
     * @param internshipId, the ID of the concerned internship
     * @throws ServletException
     * @throws IOException
     */
    private void redirectToDetailsPage(HttpServletRequest request, HttpServletResponse response, String internshipId) throws ServletException, IOException {
        if (successRequest){
            request.setAttribute("message", SUCCESS_BD);
        }else{
            request.setAttribute("message", ERR_FAILED_UPDATE_DB);
        }
        request.setAttribute("internshipData", internshipDataServices.getInternshipDetailed(internshipId));
        request.getRequestDispatcher(MISSION_PAGE).forward(request,response);
    }

    private void disableAutoCommits(DataServices... dataServices){
        for (DataServices dt : dataServices) {
            dt.disableAutoCommit();
        }
    }

    private void commitRequest(DataServices... dataServices){
        for (DataServices dt : dataServices) {
            dt.commitRequest();
        }
    }
}

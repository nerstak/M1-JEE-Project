package control;


import control.sessionBeans.*;
import modelsEntities.*;
import utils.ProcessString;
import utils.database.*;

import javax.ejb.EJB;
import javax.persistence.EntityExistsException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.UUID;

import static utils.Constants.*;

public class UpdateDetails extends ServletModel{
    @EJB
    private InternshipSessionBean internshipsSB;
    @EJB
    private KeywordsSessionBean keywordsSB;
    @EJB
    private SkillsSessionBean skillsSB;
    @EJB
    private StudentSessionBean studentSB;
    @EJB
    private CommentsSessionBean commentsSB;

    private InternshipEntity internshipEntity;
    private TutorEntity tutorEntity;

    private HttpSession session;

    private InternshipDataServices internshipDataServices;
    private CompanyDataServices companyDataServices;
    private FinalReportDataServices finalReportDataServices;
    private CommentsDataServices commentsDataServices;
    private SkillsDataServices skillsDataServices;
    private KeywordsDataServices keywordsDataServices;
    private boolean successRequest;

    @Override
    public void init() {
        super.init();
        internshipDataServices = new InternshipDataServices(dbUser, dbPwd, dbUrl);
        finalReportDataServices = new FinalReportDataServices(dbUser, dbPwd, dbUrl);
        companyDataServices = new CompanyDataServices(dbUser, dbPwd, dbUrl);
        commentsDataServices = new CommentsDataServices(dbUser, dbPwd, dbUrl);
        skillsDataServices = new SkillsDataServices(dbUser, dbPwd, dbUrl);
        keywordsDataServices = new KeywordsDataServices(dbUser, dbPwd, dbUrl);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Get the name of the button that call the servlet
        session = request.getSession();
        tutorEntity = (TutorEntity) session.getAttribute("tutor");

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
                successRequest = updateKeywords(request);
                break;
            case "skills":
                successRequest = updateSkills(request);
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

        //Check if all data are not empty
        if (ProcessString.areStringEmpty(companyId, internshipId, begin, end, mds, companyAddress, companyName)){
            request.setAttribute("message", ERR_EMPTY_FIELDS);
            return false;
        }

        //Check if all data are not empty
        if (ProcessString.isDateBefore(end, begin)){
            request.setAttribute("message", ERR_DATE_AFTER);
            return false;
        }

        //Disable the autocommit of the dataservices in case of error
        DataServices.disableAutoCommits(internshipDataServices, companyDataServices);
        int rowAffectedInternship = internshipDataServices.updateInternshipFromCompanyDetailsPage(internshipId, Date.valueOf(begin), Date.valueOf(end), mds);
        int rowAffectedCompany = companyDataServices.updateCompany(companyId, companyName, companyAddress);

        if ((rowAffectedCompany == 1) && (rowAffectedInternship == 1)){ //if all the data has been updates => commit the request
            DataServices.commitRequest(internshipDataServices, companyDataServices);
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

        StudentEntity student = new StudentEntity();
        student.setStudentId(studentId);
        student.setStudentGroup(group);
        student.setLinkedinProfile(linkedin);
        student.setFirstname(firstName);
        student.setName(lastName);
        student.setEmail(email);
        student.setTutorEntity(tutorEntity);

        //Check if data are empty (expect linkedin url)
        if(ProcessString.areStringEmpty(studentId.toString(), firstName, lastName, email, group)){
            request.setAttribute("message", ERR_EMPTY_FIELDS);
            return false;
        }
        studentSB.save(student);

        return true;
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


        //Check if data(IDs) are empty
        if(ProcessString.areStringEmpty(titleId, commentsId, internshipId)){
            request.setAttribute("message", ERR_EMPTY_FIELDS);
            return false;
        }

        internshipEntity = internshipsSB.find(UUID.fromString(internshipId));
        internshipEntity.setDescription(description);
        internshipEntity.getFinalReport().setTitle(title);
        CommentsEntity comments = internshipEntity.getComments();
        comments.setStudentComm(studentComments);
        comments.setSupervisorComm(tutorComments);
        commentsSB.save(comments);
        internshipsSB.save(internshipEntity);

        return true;
    }

    /**
     * Get the information from the form and update the skill and student_to_skills table
     * @param request, servlet request
     * @return true if the database has been updated
     */
    private boolean updateSkills(HttpServletRequest request)  {
        //Get the skill from the form
        String skill = request.getParameter("skill");

        //Check if skill is empty
        if (skill.isEmpty()){
            request.setAttribute("message", ERR_EMPTY_FIELDS);
            return false;
        }

        //Capitalize the first letter
        skill = ProcessString.capitalizeAndLowerCase(skill);
        String studentId = request.getParameter("studentId");

        SkillsEntity skillsEntity = skillsSB.getSkillByName(skill);
        try {
            if (skillsEntity == null) {
                // Creating skill if not existing
                skillsEntity = new SkillsEntity();
                skillsEntity.setSkillId(UUID.randomUUID());
                skillsEntity.setSkill(skill);
                skillsSB.save(skillsEntity);
            }

            // Adding skill to student
            StudentEntity student = studentSB.find(UUID.fromString(studentId));
            student.getSkills().add(skillsEntity);
            studentSB.save(student);
            return true;
        } catch (EntityExistsException e) {
            return false;
        }
    }

    /**
     * Get the information from the form and update the keyword and internship_to_keywords table
     * @param request, servlet request
     * @return true if the database has been updated
     */
    private boolean updateKeywords(HttpServletRequest request){
        //Get the skill from the form
        String keyword = request.getParameter("keyword");

        //Check if skill is empty
        if (keyword.isEmpty()){
            request.setAttribute("message", ERR_EMPTY_FIELDS);
            return false;
        }

        //Capitalize the first letter
        keyword = ProcessString.capitalizeAndLowerCase(keyword);
        String internshipId = request.getParameter("internshipId");

        KeywordsEntity keywordsEntity = keywordsSB.getKeywordByName(keyword);
        try {
            if (keywordsEntity == null) {
                // Creating skill if not existing
                keywordsEntity = new KeywordsEntity();
                keywordsEntity.setKeywordId(UUID.randomUUID());
                keywordsEntity.setKeyword(keyword);
                keywordsSB.save(keywordsEntity);
            }

            // Adding skill to student
            internshipEntity = internshipsSB.find(UUID.fromString(internshipId));
            internshipEntity.getListKeywords().add(keywordsEntity);
            internshipsSB.save(internshipEntity);
            return true;
        } catch (EntityExistsException e) {
            return false;
        }
    }

    /**
     * Redirect to details jsp
     * @param request, the request
     * @param response, response
     * @param internshipId, the ID of the concerned internship
     * @throws ServletException ServletException
     * @throws IOException IOException
     */
    private void redirectToDetailsPage(HttpServletRequest request, HttpServletResponse response, String internshipId) throws ServletException, IOException {
        if (successRequest){
            request.setAttribute("message", SUCCESS_BD);
        }else{
            if(request.getAttribute("message") == null){
                request.setAttribute("message", ERR_FAILED_UPDATE_DB);
            }
        }

        internshipEntity = internshipsSB.find(UUID.fromString(internshipId));

        //Set request attributes
        request.setAttribute("internshipData", internshipEntity);
        request.setAttribute("listOfSkills", skillsSB.getSkills());
        request.setAttribute("listOfKeywords",keywordsSB.getKeywords());

        request.getRequestDispatcher(MISSION_PAGE).forward(request,response);
    }
}

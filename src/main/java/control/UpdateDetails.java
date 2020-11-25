package control;


import control.sessionBeans.InternshipSessionBean;
import control.sessionBeans.KeywordsSessionBean;
import control.sessionBeans.SkillsSessionBean;
import control.sessionBeans.StudentSessionBean;
import model.InternshipData;
import model.Student;
import modelsEntities.InternshipEntity;
import modelsEntities.StudentEntity;
import utils.ProcessString;
import utils.database.*;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    private InternshipEntity internshipEntity;

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

        //Check if data are empty (expect linkedin url)
        if(ProcessString.areStringEmpty(studentId.toString(), firstName, lastName, email, group)){
            request.setAttribute("message", ERR_EMPTY_FIELDS);
            return false;
        }

        return studentSB.saveStudent(student);
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


        DataServices.disableAutoCommits(internshipDataServices, finalReportDataServices, commentsDataServices);

        int rowAffectedInternship = internshipDataServices.updateInternshipDescription(internshipId, description);
        int rowAffectedFinalReport = finalReportDataServices.updateTitleReport(titleId, title);
        int rowAffectedComments = commentsDataServices.updateComments(commentsId, studentComments, tutorComments);

        if (((rowAffectedFinalReport == 1) && (rowAffectedInternship == 1)) && (rowAffectedComments == 1)){
            DataServices.commitRequest(internshipDataServices, finalReportDataServices, commentsDataServices);
            return true;
        }else{
            return false;
        }
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

        ResultSet resultSet = skillsDataServices.selectASkill(skill);
        DataServices.disableAutoCommits(skillsDataServices);
        if (resultSet != null){
            try {
                if(resultSet.next()){ //if the skill is already in the DB
                    String skillIdDb = resultSet.getString("skill_id");
                    //Check if the skill is already linked to the student
                    resultSet = skillsDataServices.selectAStudentToSkillCouple(studentId, skillIdDb);
                    if(resultSet != null){
                        if(!resultSet.next()){
                            //Insert the Skill_id + student_id inside the Student_to_skill table
                            if (skillsDataServices.insertIntoStudentToSkill(studentId, skillIdDb) == 1){//If row is added to the db => commit the request
                                DataServices.commitRequest(skillsDataServices);
                                return true;
                            }
                        }
                    }
                }else{
                    //Add the skill inside Skills + add couple Id inside Student_to_skill
                    UUID skillId = UUID.randomUUID();
                    if  ((skillsDataServices.insertIntoSkill(skillId, skill) == 1) && (skillsDataServices.insertIntoStudentToSkill(studentId, skillId.toString())) == 1){
                        DataServices.commitRequest(skillsDataServices);
                        return true;
                    }
                }
            }
            catch (SQLException e){
                e.printStackTrace();
                return false;
            }
        }
        return false;
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

        ResultSet resultSet = keywordsDataServices.selectAKeyword(keyword);
        DataServices.disableAutoCommits(keywordsDataServices);
        if (resultSet != null){
            try {
                if(resultSet.next()){ //if the keyword is already in the DB
                    String keywordId = resultSet.getString("keyword_id");
                    //Check if the skill is already linked to the student
                    resultSet = keywordsDataServices.selectAInternshipToKeywordsCouple(internshipId, keywordId);
                    if(resultSet != null){
                        if(!resultSet.next()){
                            //Insert the Skill_id + student_id inside the Student_to_skill table
                            if (keywordsDataServices.insertIntoInternshipToKeywords(internshipId, keywordId) == 1){//If row is added to the db => commit the request
                                DataServices.commitRequest(keywordsDataServices);
                                return true;
                            }
                        }
                    }
                }else{
                    //Add the skill inside Skills + add couple Id inside Student_to_skill
                    UUID keywordId = UUID.randomUUID();
                    if  ((keywordsDataServices.insertIntoKeyword(keywordId, keyword) == 1) && (keywordsDataServices.insertIntoInternshipToKeywords(internshipId, keywordId.toString())) == 1){
                        DataServices.commitRequest(keywordsDataServices);
                        return true;
                    }
                }
            }
            catch (SQLException e){
                e.printStackTrace();
                return false;
            }
        }
        return false;
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

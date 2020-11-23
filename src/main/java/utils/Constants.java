package utils;

public class Constants {
    /* Properties */
    public static final String DB_PROPERTIES = "/WEB-INF/db.properties";

    /* Pages */
    public static final String LOGIN_PAGE = "./WEB-INF/login.jsp";
    public static final String HOME_PAGE = "./WEB-INF/homepage.jsp";
    public static final String MISSION_PAGE = "./WEB-INF/mission-details.jsp";


    /* Errors */
    public static final String ERR_INV_CRED_MESS = "Invalid credentials!";
    public static final String ERR_MISSING_FIELD = "At least one field is missing";
    public static final String ERR_FAILED_UPDATE_DB = "The database could not be updated, try again";
    public static final String ERR_EMPTY_FIELDS = "Fields can't be empty";
    public static final String ERR_DATE_AFTER = "The begin date can't be after end date";

    /*Information*/
    public static final String SUCCESS_BD = "The database has been updated";

    /* Database interactions */
    /* SELECT */
    public static final String DB_SELECT_SINGLE_TUTOR = "SELECT * FROM tutor WHERE email  = ? AND pwd = ?";
    public static final String DB_SELECT_INTERNSHIPS = "SELECT * FROM internships_data WHERE tutor_id = ? " +
                                                            "AND EXTRACT( year FROM beginning) = ? " +
                                                            "AND firstname || ' ' || name LIKE '%' || ? || '%' " +
                                                            "AND internship_id IN (SELECT itk.internship_id FROM internship_to_keywords itk JOIN keywords k ON itk.keyword_id = k.keyword_id WHERE k.keyword LIKE ?);";
    public static final String DB_SELECT_INTERNSHIP_DETAILED = "SELECT * FROM internships_data_details WHERE internship_id = ?";
    public static final String DB_SELECT_SKILLS = "SELECT * FROM skills";
    public static final String DB_SELECT_KEYWORDS = "SELECT * FROM keywords";
    public static final String DB_SELECT_STUDENT_SKILLS = "SELECT * FROM students_skills WHERE student_id = ?";
    public static final String DB_SELECT_STUDENTS_WITH_SKILL = "SELECT * FROM students_skills WHERE skill_id = ?";
    public static final String DB_SELECT_STUDENTS_SKILLS_ALL = "SELECT STS.skill_id as skill_id, skills.skill as skill FROM  student_to_skills as STS inner join skills on skills.skill_id = STS.skill_id where STS.student_id =  ?";
    public static final String DB_SELECT_INTERNSHIP_KEYWORDS_ALL = "SELECT ITK.keyword_id as keyword_id, k.keyword as keyword FROM  internship_to_keywords as ITK inner join keywords k on ITK.keyword_id = k.keyword_id  where ITK.internship_id =  ?";
    public static final String DB_SELECT_A_SKILL = "SELECT * FROM skills WHERE skill = ?";
    public static final String DB_SELECT_A_STUDENT_TO_SKILL_COUPLE = "SELECT * FROM student_to_skills WHERE student_id = ? AND skill_id = ?";
    public static final String DB_SELECT_A_KEYWORD = "SELECT * FROM keywords WHERE keyword = ?";
    public static final String DB_SELECT_A_INTERNSHIP_TO_KEYWORDS_COUPLE = "SELECT * FROM internship_to_keywords WHERE internship_id = ? AND keyword_id = ?";


    /* UPDATE */
    public static final String DB_UPDATE_COMMENTS = "UPDATE comments SET supervisor_comm = ?, student_comm = ? WHERE comments_id = ?";
    public static final String DB_UPDATE_COMPANY = "UPDATE company SET name = ?, address = ? WHERE company_id = ?";
    public static final String DB_UPDATE_FINAL_REPORT = "UPDATE final_report SET title = ?, report = ? WHERE internship_id = ?";
    public static final String DB_UPDATE_MARK = "UPDATE marks SET tech = ?, communication = ? WHERE marks_id = ?";
    public static final String DB_UPDATE_STUDENT = "UPDATE student SET name = ?, firstname = ?, email = ?, linkedin_profile = ?, student_group = ? WHERE student_id = ?";
    public static final String DB_UPDATE_STUDENT_NAME_GROUP = "UPDATE student SET name = ?, firstname = ?, student_group = ? WHERE student_id = ?";
    public static final String DB_UPDATE_VISIT = "UPDATE visit SET done = ?, planned = ?, visit_report = ? WHERE internship_id = ?";
    public static final String DB_UPDATE_VISIT_PART = "UPDATE visit SET done = ?, planned = ? WHERE visit_id = ?";
    public static final String DB_UPDATE_INTERNSHIP_DETAILS_COMPANY = "UPDATE internship SET beginning = ?, ending = ?, intern_supervisor = ? WHERE internship_id = ?";
    public static final String DB_UPDATE_INTERNSHIP_DESCRIPTION = "UPDATE internship SET description = ? WHERE internship_id = ?";
    public static final String DB_UPDATE_INTERNSHIP_HOMEPAGE = "UPDATE internship SET beginning = ?, ending = ?, intern_supervisor = ?, defense = ?, web_survey= ?, company_eval = ?, cdc = ? WHERE internship_id = ?";
    public static final String DB_UPDATE_REPORT_TITLE = "UPDATE final_report SET title = ? WHERE final_report_id = ?";
    public static final String DB_UPDATE_REPORT_BOOLEAN = "UPDATE final_report SET report = ? WHERE final_report_id = ?";


    /*INSERT INTO*/
    public static final String DB_INSERT_INTO_STUDENT_TO_SKILL = "INSERT INTO student_to_skills(student_id, skill_id) VALUES (?, ?)";
    public static final String DB_INSERT_INTO_SKILLS = "INSERT INTO skills(skill_id, skill) VALUES (?, ?)";
    public static final String DB_INSERT_INTO_INTERNSHIP_TO_KEYWORDS = "INSERT INTO internship_to_keywords(internship_id, keyword_id) VALUES (?, ?)";
    public static final String DB_INSERT_INTO_KEYWORDS = "INSERT INTO keywords(keyword_id, keyword) VALUES (?, ?)";
}

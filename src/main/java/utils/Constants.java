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

    /* UPDATE */
    public static final String DB_UPDATE_COMMENTS = "UPDATE comments SET supervisor_comm = ?, student_comm = ? WHERE internship_id = ?";
    public static final String DB_UPDATE_COMPANY = "UPDATE company SET name = ?, address = ? WHERE company_id = ?";
    public static final String DB_UPDATE_FINAL_REPORT = "UPDATE final_report SET title = ?, report = ? WHERE internship_id = ?";
    public static final String DB_UPDATE_MARKS = "UPDATE marks SET tech = ?, communication = ? WHERE internship_id = ?";
    public static final String DB_UPDATE_STUDENT = "UPDATE student SET name = ?, firstname = ?, email = ?, linkedin_profile = ?, student_group = ? WHERE student_id = ?";
    public static final String DB_UPDATE_VISIT = "UPDATE visit SET done = ?, planned = ?, visit_report = ? WHERE internship_id = ?";
}

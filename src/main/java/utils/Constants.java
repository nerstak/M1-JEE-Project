package utils;

public class Constants {
    public static final String DB_PROPERTIES = "/WEB-INF/db.properties";

    public static final String WELCOME_PAGE = "/WEB-INF/welcome.jsp";
    public static final String LOGIN_PAGE = "./WEB-INF/login.jsp";
    public static final String HOME_PAGE = "./WEB-INF/homepage.jsp";
    public static final String MISSION_PAGE = "./WEB-INF/mission-details.jsp";

    public static final String ERR_INV_CRED_MESS = "Invalid credentials!";
    public static final String ERR_MISSING_FIELD = "At least one field is missing";

    public static final String DB_SELECT_SINGLE_TUTOR = "SELECT * FROM \"Tutor\" WHERE \"Tutor\".\"Email\"  = ? AND \"Tutor\".\"Pwd\" = ?";
    public static final String DB_SELECT_INTERNSHIPS = "SELECT * FROM internship_data WHERE \"TutorId\" = ? ";
    public static final String DB_SELECT_INTERNSHIP_DETAILED = "SELECT * FROM internships_data_details WHERE \"InternshipId\" = ?";
    public static final String DB_SELECT_SKILLS = "SELECT * FROM \"Skills\"";
    public static final String DB_SELECT_STUDENT_SKILLS = "SELECT * FROM students_skills WHERE \"StudentId\" = ?";
    public static final String DB_SELECT_STUDENTS_WITH_SKILL = "SELECT * FROM students_skills WHERE \"SkillsId\" = ?";

    public static final String DB_UPDATE_COMMENTS = "UPDATE \"Comments\" SET \"SupervisorComm\" = ?, \"StudentComm\" = ? WHERE \"InternshipId\" = ?";
    public static final String DB_UPDATE_COMPANY = "UPDATE \"Company\" SET \"Name\" = ?, \"Address\" = ? WHERE \"CompanyId\" = ?";
    public static final String DB_UPDATE_FINAL_REPORT = "UPDATE \"FinalReport\" SET \"Title\" = ?, \"Report\" = ? WHERE \"InternshipId\" = ?";
    public static final String DB_UPDATE_MARKS = "UPDATE \"Marks\" SET \"Tech\" = ?, \"Communication\" = ? WHERE \"InternshipId\" = ?";
    public static final String DB_UPDATE_STUDENT = "UPDATE \"Student\" SET \"Name\" = ?, \"Firstname\" = ?, \"Email\" = ?, \"LinkedInProfile\" = ?, \"Group\" = ? WHERE \"StudentId\" = ?";
    public static final String DB_UPDATE_VISIT = "UPDATE \"Visit\" SET \"Done\" = ?, \"Planned\" = ?, \"VisitReport\" = ? WHERE \"InternshipId\" = ?";
}

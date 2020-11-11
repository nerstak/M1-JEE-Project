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
    public static final String DB_SELECT_STUDENTS = "SELECT * FROM students_table WHERE \"TutorId\" = ? ";
}

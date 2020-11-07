package utils;

public class Constants {
    public static final String WELCOME_PAGE = "/WEB-INF/welcome.jsp";
    public static final String LOGIN_PAGE = "./WEB-INF/login.jsp";
    public static final String HOME_PAGE = "./WEB-INF/homepage.jsp";
    public static final String MISSION_PAGE = "./WEB-INF/mission-details.jsp";
    public static final String ERR_INV_CRED_MESS = "Invalid credentials!";
    public static final String ERR_MISSING_FIELD = "At least one field is missing";
    public static final String TUTOR_PASSWORD = "SELECT \"Tutor\".\"Pwd\" FROM \"Tutor\" WHERE \"Tutor\".\"Email\"  = ?";
}

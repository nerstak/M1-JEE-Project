package utils;

public class Constants {
    public static final String LOGIN_PAGE = "/WEB-INF/login.jsp";
    public static final String HOMEPAGE_PAGE = "/WEB-INF/homepage.jsp";

    public static final String ERR_INV_CRED_MESS = "Invalid credentials!";

    public static final String TUTOR_PASSWORD = "SELECT \"Tutor\".\"Pwd\" FROM \"Tutor\" WHERE \"Tutor\".\"Email\"  = ?";
}

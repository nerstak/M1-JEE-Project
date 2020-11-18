package utils.database;

import model.*;
import model.InternshipData;

import java.sql.*;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import static utils.Constants.*;

/**
 * Mother-class of interaction with database
 */
public abstract class DataServices {
    protected Connection con;
    protected Statement stmt;
    protected ResultSet rs;
    protected PreparedStatement ps;

    public DataServices(String login, String pwd, String connectionUrl){
        try {
            con = DriverManager.getConnection(connectionUrl, login, pwd);
            stmt = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Select operation on DB, ONLY TO BE USED FOR DATA UNALTERED BY USERS
     * @param query to execute
     * @return ResultSet, may be null
     */
    public ResultSet selectResultSet(String query){
        try {
            rs = stmt.executeQuery(query);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get ResultSet of a parameterised query
     * @param parameter Parameter (SQL.Types.OTHER, UUID)
     * @param query Query
     * @return ResultSet (may be null)
     */
    protected ResultSet getResultSet(String parameter, String query) {
        try {
            ps = con.prepareStatement(query);
            ps.setObject(1, parameter, Types.OTHER);
            return ps.executeQuery();
        } catch (SQLException e) {
            Logger.getLogger(DataServices.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    /**
     * Create a student model from internal ResultSet
     * @return Student created
     * @throws SQLException Error when getting columns
     */
    protected Student createStudent() throws SQLException {
        Student student = new Student();

        student.setStudentId(UUID.fromString(rs.getString("student_id")));
        student.setName(rs.getString("name"));
        student.setFirstName(rs.getString("firstname"));
        student.setEmail(rs.getString("email"));
        student.setGroup(rs.getString("student_group"));
        student.setLinkedinProfile(rs.getString("linkedin_profile"));
        return student;
    }

    /**
     * Create a internship model from internal ResultSet
     * @return Internship created
     * @throws SQLException Error when getting columns
     */
    protected Internship createInternship() throws SQLException {
        Internship internship = new Internship();

        internship.setInternship(UUID.fromString(rs.getString("internship_id")));
        internship.setDesciption(rs.getString("description"));
        internship.setMidInternInfo(rs.getBoolean("mid_intern_info"));
        internship.setWebSurvey(rs.getBoolean("web_survey"));
        internship.setBegining(rs.getDate("beginning"));
        internship.setEnd(rs.getDate("ending"));
        internship.setCdc(rs.getBoolean("cdc"));
        internship.setDefense(rs.getBoolean("defense"));
        internship.setCompanyEval(rs.getBoolean("company_eval"));
        internship.setInternSupervisor(rs.getString("intern_supervisor"));

        return internship;
    }

    /**
     * Create a company model from internal ResultSet
     * @return Company created
     * @throws SQLException Error when getting columns
     */
    protected Company createCompany() throws SQLException {
        Company company = new Company();

        company.setCompanyId(UUID.fromString(rs.getString("company_id")));
        company.setName(rs.getString("company_name"));
        company.setAddress(rs.getString("address"));

        return company;
    }

    /**
     * Create a visit model from internal ResultSet
     * @return Visit created
     * @throws SQLException Error when getting columns
     */
    protected Visit createVisit() throws SQLException {
        Visit visit = new Visit();

        visit.setVisitID(UUID.fromString(rs.getString("visit_id")));
        visit.setDone(rs.getBoolean("done"));
        visit.setPlanned(rs.getBoolean("planned"));
        visit.setVisitReport(rs.getBoolean("visit_report"));

        return visit;
    }

    /**
     * Create a marks model from internal ResultSet
     * @return Marks created
     * @throws SQLException Error when getting columns
     */
    protected Marks createMarks() throws SQLException {
        Marks marks = new Marks();

        marks.setMarksId(UUID.fromString(rs.getString("marks_id")));
        marks.setCommunication(rs.getInt("communication"));
        marks.setTech(rs.getInt("tech"));

        return marks;
    }

    /**
     * Create a comments model from internal ResultSet
     * @return Comments created
     * @throws SQLException Error when getting columns
     */
    protected Comments createComments() throws SQLException {
        Comments comments = new Comments();

        comments.setCommentsId(UUID.fromString(rs.getString("comments_id")));
        comments.setStudentComm(rs.getString("student_comm"));
        comments.setSupervisorComment(rs.getString("supervisor_comm"));

        return comments;
    }

    /**
     * Create a final report model from internal ResultSet
     * @return FinalReport created
     * @throws SQLException Error when getting columns
     */
    protected FinalReport createFinalReport() throws SQLException {
        FinalReport finalReport = new FinalReport();

        finalReport.setFinalReportId(UUID.fromString(rs.getString("final_report_id")));
        finalReport.setReport(rs.getBoolean("report"));
        finalReport.setTitle(rs.getString("title"));

        return finalReport;
    }

    /**
     * Active l'autocommit des requêtes
     */
    public void enableAutoCommit(){
        try {
            con.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Desactive l'autocommit des requêtes
     */
    public void disableAutoCommit(){
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Commit les requêtes
     */
    public void commitRequest(){
        try {
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Rollback les requêtes
     */
    public void rollbackRequest(){
        try {
            con.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

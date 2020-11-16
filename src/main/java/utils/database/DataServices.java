package utils.database;

import model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static utils.Constants.*;

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
     * Select operation on DB
     * @param query, query to do
     * @return resultset
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
     * Select internships supervised by a tutor
     * @param tutorId Supervisor
     * @param year Beginning year of internship
     * @param name Part of first/last name
     * @param keyword Keyword
     * @return List of internships (may be null)
     */
    public ResultSet selectInternships(String tutorId, int year, String name, String keyword) {
        try {
            ps = con.prepareStatement(DB_SELECT_INTERNSHIPS);
            ps.setObject(1, tutorId, Types.OTHER);
            ps.setInt(2, year);
            ps.setString(3, name);
            ps.setString(4, keyword);

            return ps.executeQuery();
        } catch (SQLException e) {
            Logger.getLogger(DataServices.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    /**
     * Select internship and its details
     * @param internshipId Internship
     * @return Details (may be null)
     */
    public ResultSet selectInternshipDetailed(String internshipId) {
        return getResultSet(internshipId, DB_SELECT_INTERNSHIP_DETAILED);
    }

    /**
     * Select student's skill
     * @param studentId ID of the student
     * @return result set of the query
     */
    public ResultSet selectStudentSkillsAll(String studentId) {
        return getResultSet(studentId, DB_SELECT_STUDENTS_SKILLS_ALL);
    }

    /**
     * Get ResultSet of a parameterised query
     * @param parameter Parameter
     * @param query Query
     * @return ResultSet (may be null)
     */
    private ResultSet getResultSet(String parameter, String query) {
        try {
            ps = con.prepareStatement(query);
            ps.setObject(1, parameter, Types.OTHER);
            return ps.executeQuery();
        } catch (SQLException e) {
            Logger.getLogger(DataServices.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
}

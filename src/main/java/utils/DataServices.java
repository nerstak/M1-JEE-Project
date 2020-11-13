package utils;

import model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static utils.Constants.*;

public class DataServices {
    private Connection con;
    private Statement stmt;
    private ResultSet rs;
    private PreparedStatement ps;

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
     * @return ArrayList<Object[]>, arrayList of String's array => Each Object[] contain all columns of one line
     */
    public ArrayList<Object[]> select(String query){
        ArrayList<Object[]> list = new ArrayList<>();
        try {
            ResultSet resultSet = stmt.executeQuery(query);
            ResultSetMetaData resultMeta = resultSet.getMetaData();

            //get rows
            while(resultSet.next()){
                Object[] array = new String[resultMeta.getColumnCount()];
                for (int i = 1; i <= resultMeta.getColumnCount(); i++) { //de 1 à taille car index commence à 1 et pas à 0
                    array[i-1] = resultSet.getString(i);
                }
                list.add(array);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
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
     * Select a tutor using email and password
     * @param email Email of the tutor
     * @param password Password of the tutor
     * @return ResultSet (may be null)
     */
    public ResultSet selectTutor(String email, String password) {
        try {
            ps = con.prepareStatement(DB_SELECT_SINGLE_TUTOR);
            ps.setString(1, email);
            ps.setString(2, password);

            return ps.executeQuery();
        } catch (SQLException e) {
            Logger.getLogger(DataServices.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    /**
     * Select internships supervised by a tutor
     * @param tutorId Supervisor
     * @param year Beginning year of internship
     * @return List of internships (may be null)
     */
    public ResultSet selectInternships(String tutorId, int year) {
        try {
            ps = con.prepareStatement(DB_SELECT_INTERNSHIPS);
            ps.setObject(1, tutorId, Types.OTHER);
            ps.setInt(2, year);

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

    /**
     * Update student in Database
     * //TODO: Maybe move this to student object, and generalise the function
     * @param s Student
     * @return Row count impacted
     */
    public int updateStudent(Student s) {
        try {
            ps = con.prepareStatement(DB_UPDATE_STUDENT);
            ps.setString(1,s.getName());
            ps.setString(2,s.getFirstName());
            ps.setString(3,s.getEmail());
            ps.setString(4,s.getLinkedinProfile());
            ps.setString(5,s.getGroup());
            ps.setObject(6,s.getStudentId(), Types.OTHER);

            return ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DataServices.class.getName()).log(Level.SEVERE, null, e);
        }
        return 0;
    }



    //Enable autocommit
    public void enableAutoCommit(){
        try {
            con.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Disable autocommit
    public void disableAutoCommit(){
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Commit request
    public void commitRequest(){
        try {
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Rollback request
    public void rollbackRequest(){
        try {
            con.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Close connection
    public void closeRequest(){
        try {
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

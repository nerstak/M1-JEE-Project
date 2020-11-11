package utils;

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

    public ResultSet selectStudents(String tutorId) {
        return getResultSet(tutorId, DB_SELECT_STUDENTS);
    }

    public ResultSet selectInternship(String internshipId) {
        return getResultSet(internshipId, DB_SELECT_INTERNSHIP);
    }

    private ResultSet getResultSet(String internshipId, String dbSelectInternship) {
        try {
            ps = con.prepareStatement(dbSelectInternship);
            ps.setString(1, internshipId);

            return ps.executeQuery();
        } catch (SQLException e) {
            Logger.getLogger(DataServices.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
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

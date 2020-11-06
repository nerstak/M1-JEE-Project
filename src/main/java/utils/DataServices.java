package utils;

import java.sql.*;
import java.util.ArrayList;

public class DataServices {
    private Connection con;
    private Statement stmt;

    public DataServices(String login, String pwd, String connectionUrl, String jdbc){
        try {
            Class.forName(jdbc);
            con = DriverManager.getConnection(connectionUrl, login, pwd);
            stmt = con.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
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
            ResultSet rs = stmt.executeQuery(query);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
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

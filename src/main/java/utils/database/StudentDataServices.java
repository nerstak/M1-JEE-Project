package utils.database;

import model.Student;

import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;

import static utils.Constants.DB_UPDATE_STUDENT;
import static utils.Constants.DB_UPDATE_STUDENT_NAME_GROUP;

/**
 * Class of interaction with database, for Students
 */
public class StudentDataServices extends DataServices {
    public StudentDataServices(String login, String pwd, String connectionUrl) {
        super(login, pwd, connectionUrl);
    }

    /**
     * Update student in Database
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

    public int updateNamesGroup(String lastName, String firstName, String group, String id){
        try {
            ps = con.prepareStatement(DB_UPDATE_STUDENT_NAME_GROUP);
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, group);
            ps.setObject(4, id, Types.OTHER);

            return ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DataServices.class.getName()).log(Level.SEVERE, null, e);
        }
        return 0;
    }
}

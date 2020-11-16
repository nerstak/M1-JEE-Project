package utils.database;

import model.Student;

import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;

import static utils.Constants.DB_UPDATE_STUDENT;

public class StudentData extends DataServices {
    public StudentData(String login, String pwd, String connectionUrl) {
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

}

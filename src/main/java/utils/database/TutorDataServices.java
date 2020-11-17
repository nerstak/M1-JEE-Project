package utils.database;

import model.Tutor;

import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import static utils.Constants.DB_SELECT_SINGLE_TUTOR;

/**
 * Class of interaction with database, for TutorData
 */
public class TutorDataServices extends DataServices{
    public TutorDataServices(String login, String pwd, String connectionUrl) {
        super(login, pwd, connectionUrl);
    }

    /**
     * Select a tutor using email and password
     * @param tutor Tutor to select information
     * @return Validity of credentials
     */
    public boolean selectTutor(Tutor tutor) {
        try {
            // Database interaction
            ps = con.prepareStatement(DB_SELECT_SINGLE_TUTOR);
            ps.setString(1, tutor.getEmail());
            ps.setString(2, tutor.getPwd());
            rs = ps.executeQuery();

            // Filling missing information
            if(rs.next()) {
                tutor.setTutorId(UUID.fromString(rs.getString("tutor_id")));
                tutor.setFirstName(rs.getString("firstname"));
                tutor.setName(rs.getString("name"));
                return true;
            }
        } catch (SQLException e) {
            Logger.getLogger(DataServices.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }
}

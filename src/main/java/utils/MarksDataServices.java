package utils;

import utils.database.DataServices;

import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;

import static utils.Constants.DB_UPDATE_MARK;
import static utils.Constants.DB_UPDATE_STUDENT;

public class MarksDataServices extends DataServices {
    public MarksDataServices(String login, String pwd, String connectionUrl) {
        super(login, pwd, connectionUrl);
    }

    public int updateMarks(String tech, String comm, String id) {
        try {
            ps = con.prepareStatement(DB_UPDATE_MARK);
            ps.setInt(1, Integer.parseInt(tech));
            ps.setInt(2, Integer.parseInt(comm));
            ps.setObject(3, id, Types.OTHER);

            return ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DataServices.class.getName()).log(Level.SEVERE, null, e);
        }
        return 0;
    }
}

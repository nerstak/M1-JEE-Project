package utils.database;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;

import static utils.Constants.DB_UPDATE_VISIT_PART;

public class VisitDataServices extends DataServices {
    public VisitDataServices(String login, String pwd, String connectionUrl) {
        super(login, pwd, connectionUrl);
    }

    public int updateVisit(String done, String planned, String visitId ) {
        try {
            ps = con.prepareStatement(DB_UPDATE_VISIT_PART);
            ps.setBoolean(1, Boolean.parseBoolean(done));
            ps.setBoolean(2, Boolean.parseBoolean(planned));
            ps.setObject(3, visitId, Types.OTHER);

            return ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DataServices.class.getName()).log(Level.SEVERE, null, e);
        }
        return 0;
    }

}

package utils.database;

import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;

import static utils.Constants.DB_UPDATE_REPORT_TITLE;

public class FinalReportDataServices extends DataServices {
    public FinalReportDataServices(String login, String pwd, String connectionUrl) {
        super(login, pwd, connectionUrl);
    }

    public int updateTitleReport(String titleId, String titleReport ) {
        try {
            ps = con.prepareStatement(DB_UPDATE_REPORT_TITLE);
            ps.setString(1, titleReport);
            ps.setObject(2, titleId, Types.OTHER);

            return ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DataServices.class.getName()).log(Level.SEVERE, null, e);
        }
        return 0;
    }

}

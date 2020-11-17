package utils.database;

import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import static utils.Constants.DB_UPDATE_COMPANY;

public class CompanyDataServices extends DataServices{
    public CompanyDataServices(String login, String pwd, String connectionUrl) {
        super(login, pwd, connectionUrl);
    }

    public boolean updateCompany(UUID companyId, String name, String address){
        try {
            // Database interaction
            ps = con.prepareStatement(DB_UPDATE_COMPANY);
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setObject(3, companyId);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 1){
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            Logger.getLogger(DataServices.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }


}

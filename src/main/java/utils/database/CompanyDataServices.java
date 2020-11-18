package utils.database;

import java.sql.SQLException;
import java.sql.Types;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import static utils.Constants.DB_UPDATE_COMPANY;

public class CompanyDataServices extends DataServices{
    public CompanyDataServices(String login, String pwd, String connectionUrl) {
        super(login, pwd, connectionUrl);
    }

    /**
     * Update the company
     * @param companyId, the company Id
     * @param name, the name of the company
     * @param address, the address of the company
     * @return the number of rows affected
     */
    public int updateCompany(String companyId, String name, String address){
        try {
            // Database interaction
            ps = con.prepareStatement(DB_UPDATE_COMPANY);
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setObject(3, companyId, Types.OTHER);
            return ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DataServices.class.getName()).log(Level.SEVERE, null, e);
        }
        return 0;
    }


}

package utils.database;

import control.Homepage;
import model.Keywords;

import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import static utils.Constants.DB_SELECT_KEYWORDS;

public class KeywordsDataServices extends DataServices {
    ArrayList<Keywords> listOfKeywords;

    public KeywordsDataServices(String login, String pwd, String connectionUrl) {
        super(login, pwd, connectionUrl);
    }

    /**
     * Get list of all keywords
     * @return List of keywords
     */
    public ArrayList<Keywords> getListOfKeywords() {
        listOfKeywords = new ArrayList<>();
        rs = selectResultSet(DB_SELECT_KEYWORDS);

        // Formatting
        if (rs != null) {
            try {
                while (rs.next()) {
                    Keywords keywords = new Keywords(rs.getString("keyword"), (UUID) rs.getObject("keyword_Id"));
                    listOfKeywords.add(keywords);
                }
            } catch (Exception e) {
                Logger.getLogger(Homepage.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return listOfKeywords;
    }
}

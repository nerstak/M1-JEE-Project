package utils.database;

import control.Homepage;
import model.Internship;
import model.Keywords;
import model.Skills;
import model.Student;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import static utils.Constants.*;

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

    /**
     * Get the list of keywords of a internship
     * @param internshipId Internship to query skills
     * @return the list of skills for the student
     */
    public ArrayList<Keywords> getInternshipKeywordsAll(String internshipId) {
        listOfKeywords = new ArrayList<>();
        rs = selectStudentSkillsAll(internshipId);

        // Formatting into a regular array
        if (rs != null) {
            try {
                while (rs.next()) {
                    Keywords keywords = new Keywords(rs.getString("keyword"), (UUID) rs.getObject("keyword_id"));
                    listOfKeywords.add(keywords);
                }
            } catch (Exception e) {
                Logger.getLogger(Homepage.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        return listOfKeywords;
    }

    /**
     * Select internship's keywords
     * @param internshipId, ID of the internship
     * @return result set of the query
     */
    private ResultSet selectStudentSkillsAll(String internshipId) {
        return getResultSet(internshipId, DB_SELECT_INTERNSHIP_KEYWORDS_ALL);
    }
}

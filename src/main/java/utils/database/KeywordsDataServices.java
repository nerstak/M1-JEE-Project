package utils.database;

import control.Homepage;
import model.Keywords;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
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
        rs = selectInternshipKeywordsAll(internshipId);

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
    private ResultSet selectInternshipKeywordsAll(String internshipId) {
        return getResultSet(internshipId, DB_SELECT_INTERNSHIP_KEYWORDS_ALL);
    }


    /**
     * Search if a keyword is inside the database
     * @param keyword, the keyword to search
     * @return result set of the query
     */
    public ResultSet selectAKeyword(String keyword) {
        return getResultSet(keyword, DB_SELECT_A_KEYWORD);
    }


    public ResultSet selectAInternshipToKeywordsCouple(String internshipId, String keywordId){
        return getResultSetCouple(internshipId, keywordId, DB_SELECT_A_INTERNSHIP_TO_KEYWORDS_COUPLE);
    }

    /**
     * Insert into internship_to_keywords table the keyword id and the internship id
     * @param internshipId, the student id
     * @param keywordIdDb, the skill id
     * @return number of row affected
     */
    public int insertIntoInternshipToKeywords(String internshipId, String keywordIdDb){
        try {
            ps = con.prepareStatement(DB_INSERT_INTO_INTERNSHIP_TO_KEYWORDS);
            ps.setObject(1, internshipId, Types.OTHER);
            ps.setObject(2, keywordIdDb, Types.OTHER);

            return ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DataServices.class.getName()).log(Level.SEVERE, null, e);
        }
        return 0;
    }

    /**
     * Insert into keywords, a keyword
     * @param keywordId, the keyword id
     * @param keyword, the keyword
     * @return Number of rows affected
     */
    public int insertIntoKeyword(UUID keywordId, String keyword){
        try {
            ps = con.prepareStatement(DB_INSERT_INTO_KEYWORDS);
            ps.setObject(1, keywordId, Types.OTHER);
            ps.setString(2, keyword);

            return ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DataServices.class.getName()).log(Level.SEVERE, null, e);
        }
        return 0;
    }

}

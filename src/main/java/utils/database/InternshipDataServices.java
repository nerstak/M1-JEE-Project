package utils.database;

import model.InternshipData;
import model.Student;
import model.Tutor;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import static utils.Constants.*;

/**
 * Class of interaction with database, for InternshipData
 */
public class InternshipDataServices extends DataServices {
    private ArrayList<InternshipData> listOfInternshipData;
    private InternshipData internshipData;

    public InternshipDataServices(String login, String pwd, String connectionUrl) {
        super(login, pwd, connectionUrl);
    }

    /**
     * Get the list of internships
     * @param tutor Tutor associated to internship
     * @param year Year of internships
     * @param name Name to look for
     * @param keyword Keyword specified
     * @return List of internships, may be empty
     */
    public ArrayList<InternshipData> getListInternships(Tutor tutor, int year, String name, String keyword) {
        listOfInternshipData = new ArrayList<>();
        rs = selectListInternships(tutor.getTutorId().toString(), year, name, keyword);


        try {
            while (rs != null && rs.next()) {
                InternshipData i = new InternshipData();

                // We fill models with data
                i.setStudent(createStudent());
                i.setInternship(createInternship());
                i.setCompany(createCompany());
                i.setVisit(createVisit());
                i.setMarks(createMarks());

                listOfInternshipData.add(i);
            }
        } catch (SQLException e) {
            Logger.getLogger(InternshipDataServices.class.getName()).log(Level.SEVERE, null, e);
        }
        return listOfInternshipData;
    }

    /**
     * Get detailed information on an Internship
     * @param internshipID ID of internship
     * @return Internship, may be null
     */
    public InternshipData getInternshipDetailed(String internshipID) {
        internshipData = new InternshipData();

        rs = selectInternshipDetailed(internshipID);
        try {
            if(rs != null && rs.next()) {

                // We fill models with data
                internshipData.setStudent(createStudent());
                internshipData.setInternship(createInternship());
                internshipData.setCompany(createCompany());
                internshipData.setVisit(createVisit());
                internshipData.setMarks(createMarks());
                internshipData.setComments(createComments());
                internshipData.setFinalReport(createFinalReport());

                return internshipData;
            }
        } catch (SQLException e) {
            Logger.getLogger(InternshipDataServices.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    /**
     * Select internships supervised by a tutor
     * @param tutorId Supervisor
     * @param year Beginning year of internship
     * @param name Part of first/last name
     * @param keyword Keyword
     * @return List of internships (may be null)
     */
    private ResultSet selectListInternships(String tutorId, int year, String name, String keyword) {
        try {
            ps = con.prepareStatement(DB_SELECT_INTERNSHIPS);
            ps.setObject(1, tutorId, Types.OTHER);
            ps.setInt(2, year);
            ps.setString(3, name);
            ps.setString(4, keyword);

            return ps.executeQuery();
        } catch (SQLException e) {
            Logger.getLogger(DataServices.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    /**
     * Select internship and its details
     * @param internshipId Internship
     * @return Details (may be null)
     */
    public ResultSet selectInternshipDetailed(String internshipId) {
        return getResultSet(internshipId, DB_SELECT_INTERNSHIP_DETAILED);
    }


    /**
     * Update partially the internship
     * @param internshipId, the ID of the internship
     * @param begin, begin date of internship
     * @param end, end date of internship
     * @param mds, intern supervisor
     * @return number of rows affected
     */
    public int updateInternshipDetailsPage(String internshipId, Date begin, Date end, String mds ) {
        try {
            ps = con.prepareStatement(DB_UPDATE_INTERNSHIP_DETAILS);
            ps.setDate(1, begin);
            ps.setDate(2, end);
            ps.setString(3, mds);
            ps.setObject(4, internshipId, Types.OTHER);

            return ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DataServices.class.getName()).log(Level.SEVERE, null, e);
        }
        return 0;
    }
}

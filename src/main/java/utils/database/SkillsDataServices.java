package utils.database;

import control.Homepage;
import model.Skills;
import model.Student;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import static utils.Constants.DB_SELECT_SKILLS;
import static utils.Constants.DB_SELECT_STUDENTS_SKILLS_ALL;

public class SkillsDataServices extends DataServices {
    private ArrayList<Skills> listOfSkills;

    public SkillsDataServices(String login, String pwd, String connectionUrl) {
        super(login, pwd, connectionUrl);
    }

    /**
     * Get the list of all skills
     * @return List of skills
     */
    private ArrayList<Skills> getListOfSkills() {
        listOfSkills = new ArrayList<>();
        rs = selectResultSet(DB_SELECT_SKILLS);

        // Formatting into a regular array
        if (rs != null) {
            try {
                while (rs.next()) {
                    Skills skills = new Skills(rs.getString("Skill"), (UUID) rs.getObject("Skill_Id"));
                    listOfSkills.add(skills);
                }
            } catch (Exception e) {
                Logger.getLogger(Homepage.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return listOfSkills;
    }

    /**
     * Get the list of skills of a student
     * @param st Student to query skills
     * @return the list of skills for the student
     */
    public ArrayList<Skills> getStudentSkillsAll(Student st) {
        listOfSkills = new ArrayList<>();
        rs = selectStudentSkillsAll(st.getStudentId().toString());

        // Formatting into a regular array
        if (rs != null) {
            try {
                while (rs.next()) {
                    Skills skills = new Skills(rs.getString("skill"), (UUID) rs.getObject("skill_id"));
                    listOfSkills.add(skills);
                }
            } catch (Exception e) {
                Logger.getLogger(Homepage.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        return listOfSkills;
    }

    /**
     * Select student's skill
     * @param studentId ID of the student
     * @return result set of the query
     */
    private ResultSet selectStudentSkillsAll(String studentId) {
        return getResultSet(studentId, DB_SELECT_STUDENTS_SKILLS_ALL);
    }
}

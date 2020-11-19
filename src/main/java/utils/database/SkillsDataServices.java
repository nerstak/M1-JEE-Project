package utils.database;

import control.Homepage;
import model.Skills;
import model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import static utils.Constants.*;

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

    /**
     * Search if a skill is inside the databse
     * @param skill, the skill to search
     * @return result set of the query
     */
    public ResultSet selectASkill(String skill) {
        return getResultSet(skill, DB_SELECT_A_SKILL);
    }


    public ResultSet selectAStudentToSkillCouple(String studentId, String skillId){
        return getResultSetCouple(studentId, skillId, DB_SELECT_A_STUDENT_TO_SKILL_COUPLE);
    }

    /**
     * Insert into student_to_skill table the skill id and the student id
     * @param studentId, the student id
     * @param skillIdDb, the skill id
     * @return number of row affected
     */
    public int insertIntoStudentToSkill(String studentId, String skillIdDb){
        try {
            ps = con.prepareStatement(DB_INSERT_INTO_STUDENT_TO_SKILL);
            ps.setObject(1, studentId, Types.OTHER);
            ps.setObject(2, skillIdDb, Types.OTHER);

            return ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DataServices.class.getName()).log(Level.SEVERE, null, e);
        }
        return 0;
    }

    /**
     * Insert into skill, a skill
     * @param skillId, the skill id
     * @param skill, the skill
     * @return, the number of rows affected
     */
    public int insertIntoSkill(UUID skillId, String skill){
        try {
            ps = con.prepareStatement(DB_INSERT_INTO_SKILLS);
            ps.setObject(1, skillId, Types.OTHER);
            ps.setString(2, skill);

            return ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DataServices.class.getName()).log(Level.SEVERE, null, e);
        }
        return 0;
    }


}

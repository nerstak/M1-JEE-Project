package modelsEntities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class StudentToSkillsEntityPK implements Serializable {
    private String studentId;
    private String skillId;

    @Column(name = "student_id", nullable = false)
    @Id
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Column(name = "skill_id", nullable = false)
    @Id
    public String getSkillId() {
        return skillId;
    }

    public void setSkillId(String skillId) {
        this.skillId = skillId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentToSkillsEntityPK that = (StudentToSkillsEntityPK) o;
        return Objects.equals(studentId, that.studentId) &&
                Objects.equals(skillId, that.skillId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, skillId);
    }
}

package modelsEntities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "student_to_skills", schema = "public", catalog = "st2eedb")
@IdClass(StudentToSkillsEntityPK.class)
public class StudentToSkillsEntity {
    private String studentId;
    private String skillId;

    @Id
    @Column(name = "student_id", nullable = false)
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Id
    @Column(name = "skill_id", nullable = false)
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
        StudentToSkillsEntity that = (StudentToSkillsEntity) o;
        return Objects.equals(studentId, that.studentId) &&
                Objects.equals(skillId, that.skillId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, skillId);
    }
}

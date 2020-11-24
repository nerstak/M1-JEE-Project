package modelsEntities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "students_skills", schema = "public", catalog = "st2eedb")
public class StudentsSkillsEntity {
    private String skillId;
    private String skill;
    private String studentId;

    @Basic
    @Column(name = "skill_id", nullable = true)
    public String getSkillId() {
        return skillId;
    }

    public void setSkillId(String skillId) {
        this.skillId = skillId;
    }

    @Basic
    @Column(name = "skill", nullable = true, length = -1)
    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    @Basic
    @Column(name = "student_id", nullable = true)
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentsSkillsEntity that = (StudentsSkillsEntity) o;
        return Objects.equals(skillId, that.skillId) &&
                Objects.equals(skill, that.skill) &&
                Objects.equals(studentId, that.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skillId, skill, studentId);
    }
}

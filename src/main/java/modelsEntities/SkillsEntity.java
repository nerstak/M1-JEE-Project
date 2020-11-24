package modelsEntities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "skills", schema = "public", catalog = "st2eedb")
public class SkillsEntity {
    private String skillId;
    private String skill;

    @Id
    @Column(name = "skill_id", nullable = false)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkillsEntity that = (SkillsEntity) o;
        return Objects.equals(skillId, that.skillId) &&
                Objects.equals(skill, that.skill);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skillId, skill);
    }
}

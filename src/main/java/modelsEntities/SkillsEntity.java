package modelsEntities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "skills", schema = "public", catalog = "st2eedb")
public class SkillsEntity {
    @Id
    @Column(name = "skill_id", nullable = false, columnDefinition="uuid")
    private UUID skillId;

    @Basic
    @Column(name = "skill", nullable = true, length = -1)
    private String skill;


    public UUID getSkillId() {
        return skillId;
    }

    public void setSkillId(UUID skillId) {
        this.skillId = skillId;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    @ManyToMany
    @JoinTable( name = "student_to_skills",
            joinColumns = @JoinColumn( name = "skill_id" ),
            inverseJoinColumns = @JoinColumn( name = "student_id" ) )
    private List<StudentEntity> students = new ArrayList<>();

    public List getListStudents() {return students;}

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

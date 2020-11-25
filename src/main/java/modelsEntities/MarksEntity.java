package modelsEntities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "marks", schema = "public", catalog = "st2eedb")
@NamedQueries(
        @NamedQuery(name = "MarksEntity.updateMarks", query = "update MarksEntity set tech = :tech, communication = :communication Where marksId = :marksId")
)
public class MarksEntity {
    private String marksId;
    private Integer tech;
    private Integer communication;
    private String internshipId;

    @Id
    @Column(name = "marks_id", nullable = false)
    public String getMarksId() {
        return marksId;
    }

    public void setMarksId(String marksId) {
        this.marksId = marksId;
    }

    @Basic
    @Column(name = "tech", nullable = true)
    public Integer getTech() {
        return tech;
    }

    public void setTech(Integer tech) {
        this.tech = tech;
    }

    @Basic
    @Column(name = "communication", nullable = true)
    public Integer getCommunication() {
        return communication;
    }

    public void setCommunication(Integer communication) {
        this.communication = communication;
    }

    @Basic
    @Column(name = "internship_id", nullable = true)
    public String getInternshipId() {
        return internshipId;
    }

    public void setInternshipId(String internshipId) {
        this.internshipId = internshipId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarksEntity that = (MarksEntity) o;
        return Objects.equals(marksId, that.marksId) &&
                Objects.equals(tech, that.tech) &&
                Objects.equals(communication, that.communication) &&
                Objects.equals(internshipId, that.internshipId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(marksId, tech, communication, internshipId);
    }
}

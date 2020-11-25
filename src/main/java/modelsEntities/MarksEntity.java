package modelsEntities;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "marks", schema = "public", catalog = "st2eedb")
public class MarksEntity {
    @Id
    @Column(name = "marks_id", nullable = false, columnDefinition="uuid")
    private UUID marksId;

    @Basic
    @Column(name = "tech", nullable = true)
    private Integer tech;

    @Basic
    @Column(name = "communication", nullable = true)
    private Integer communication;
//    private String internshipId;


    @OneToOne
    @JoinColumn( name="internship_id", nullable=true )
    private InternshipEntity internshipEntity;

    public UUID getMarksId() {
        return marksId;
    }

    public void setMarksId(UUID marksId) {
        this.marksId = marksId;
    }

    public Integer getTech() {
        return tech;
    }

    public void setTech(Integer tech) {
        this.tech = tech;
    }

    public Integer getCommunication() {
        return communication;
    }

    public void setCommunication(Integer communication) {
        this.communication = communication;
    }


/*
    @Basic
    @Column(name = "internship_id", nullable = true)
    public String getInternshipId() {
        return internshipId;
    }

    public void setInternshipId(String internshipId) {
        this.internshipId = internshipId;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarksEntity that = (MarksEntity) o;
        return Objects.equals(marksId, that.marksId) &&
                Objects.equals(tech, that.tech) &&
                Objects.equals(communication, that.communication);// &&
//                Objects.equals(internshipId, that.internshipId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(marksId, tech, communication);
    }
}

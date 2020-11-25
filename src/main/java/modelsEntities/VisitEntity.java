package modelsEntities;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "visit", schema = "public", catalog = "st2eedb")
public class VisitEntity {
    @Id
    @Column(name = "visit_id", nullable = false, columnDefinition="uuid")
    private UUID visitId;

    @Basic
    @Column(name = "done", nullable = true)
    private Boolean done;

    @Basic
    @Column(name = "planned", nullable = true)
    private Boolean planned;

    @Basic
    @Column(name = "visit_report", nullable = true)
    private Boolean visitReport;
//    private String internshipId;

    @OneToOne
    @JoinColumn( name="internship_id", nullable=true )
    private InternshipEntity internshipEntity;


    public UUID getVisitId() {
        return visitId;
    }

    public void setVisitId(UUID visitId) {
        this.visitId = visitId;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public Boolean getPlanned() {
        return planned;
    }

    public void setPlanned(Boolean planned) {
        this.planned = planned;
    }

    public Boolean getVisitReport() {
        return visitReport;
    }

    public void setVisitReport(Boolean visitReport) {
        this.visitReport = visitReport;
    }




    /*
    @Basic
    @Column(name = "internship_id", nullable = true)
    public String getInternshipId() {
        return internshipId;
    }

    public void setInternshipId(String internshipId) {
        this.internshipId = internshipId;
    }
*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisitEntity that = (VisitEntity) o;
        return Objects.equals(visitId, that.visitId) &&
                Objects.equals(done, that.done) &&
                Objects.equals(planned, that.planned) &&
                Objects.equals(visitReport, that.visitReport);// &&
//                Objects.equals(internshipId, that.internshipId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(visitId, done, planned, visitReport);
    }
}

package modelsEntities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "visit", schema = "public", catalog = "st2eedb")
@NamedQueries(
        @NamedQuery(name = "VisitEntity.UpdateVisit", query = "update VisitEntity set done = :done, planned = :planned Where visitId = :visitId")
)
public class VisitEntity {
    private String visitId;
    private Boolean done;
    private Boolean planned;
    private Boolean visitReport;
    private String internshipId;

    @Id
    @Column(name = "visit_id", nullable = false)
    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    @Basic
    @Column(name = "done", nullable = true)
    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    @Basic
    @Column(name = "planned", nullable = true)
    public Boolean getPlanned() {
        return planned;
    }

    public void setPlanned(Boolean planned) {
        this.planned = planned;
    }

    @Basic
    @Column(name = "visit_report", nullable = true)
    public Boolean getVisitReport() {
        return visitReport;
    }

    public void setVisitReport(Boolean visitReport) {
        this.visitReport = visitReport;
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
        VisitEntity that = (VisitEntity) o;
        return Objects.equals(visitId, that.visitId) &&
                Objects.equals(done, that.done) &&
                Objects.equals(planned, that.planned) &&
                Objects.equals(visitReport, that.visitReport) &&
                Objects.equals(internshipId, that.internshipId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(visitId, done, planned, visitReport, internshipId);
    }
}

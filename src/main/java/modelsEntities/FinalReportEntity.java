package modelsEntities;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "final_report", schema = "public", catalog = "st2eedb")
public class FinalReportEntity {
    @Id
    @Column(name = "final_report_id", nullable = false, columnDefinition="uuid")
    private UUID finalReportId;

    @Basic
    @Column(name = "title", nullable = true, length = -1)
    private String title;

    @Basic
    @Column(name = "report", nullable = true)
    private Boolean report;
    //private String internshipId;

    @OneToOne
    @JoinColumn( name="internship_id", nullable=true )
    InternshipEntity internshipEntity;


    public UUID getFinalReportId() {
        return finalReportId;
    }

    public void setFinalReportId(UUID finalReportId) {
        this.finalReportId = finalReportId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getReport() {
        return report;
    }

    public void setReport(Boolean report) {
        this.report = report;
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
        FinalReportEntity that = (FinalReportEntity) o;
        return Objects.equals(finalReportId, that.finalReportId) &&
                Objects.equals(title, that.title) &&
                Objects.equals(report, that.report);// &&
//                Objects.equals(internshipId, that.internshipId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(finalReportId, title, report);
    }
}

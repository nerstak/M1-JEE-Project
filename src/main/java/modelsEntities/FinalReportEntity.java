package modelsEntities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "final_report", schema = "public", catalog = "st2eedb")
@NamedQueries(
        @NamedQuery(name = "FinalReportEntity.updateTitleReport", query = "update FinalReportEntity set title = :title Where finalReportId = :finalReportId"),
        @NamedQuery(name = "FinalReportEntity.updateReportBool", query = "update FinalReportEntity set report = :report Where finalReportId = :finalReportId")
)
public class FinalReportEntity {
    private String finalReportId;
    private String title;
    private Boolean report;
    private String internshipId;

    @Id
    @Column(name = "final_report_id", nullable = false)
    public String getFinalReportId() {
        return finalReportId;
    }

    public void setFinalReportId(String finalReportId) {
        this.finalReportId = finalReportId;
    }

    @Basic
    @Column(name = "title", nullable = true, length = -1)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "report", nullable = true)
    public Boolean getReport() {
        return report;
    }

    public void setReport(Boolean report) {
        this.report = report;
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
        FinalReportEntity that = (FinalReportEntity) o;
        return Objects.equals(finalReportId, that.finalReportId) &&
                Objects.equals(title, that.title) &&
                Objects.equals(report, that.report) &&
                Objects.equals(internshipId, that.internshipId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(finalReportId, title, report, internshipId);
    }
}

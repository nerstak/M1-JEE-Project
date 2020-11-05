package model;

import java.util.UUID;

public class FinalReport {
    private UUID finalReportId;
    private String title;
    private boolean report;
    private UUID internshipId;

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

    public boolean isReport() {
        return report;
    }

    public void setReport(boolean report) {
        this.report = report;
    }

    public UUID getInternshipId() {
        return internshipId;
    }

    public void setInternshipId(UUID internshipId) {
        this.internshipId = internshipId;
    }
}

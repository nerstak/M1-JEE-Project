package model;

import java.util.UUID;

public class Visit {
    private UUID visitID;
    private boolean done;
    private boolean planned;
    private boolean visitReport;
    private boolean internshipId;

    public UUID getVisitID() {
        return visitID;
    }

    public void setVisitID(UUID visitID) {
        this.visitID = visitID;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public boolean isPlanned() {
        return planned;
    }

    public void setPlanned(boolean planned) {
        this.planned = planned;
    }

    public boolean isVisitReport() {
        return visitReport;
    }

    public void setVisitReport(boolean visitReport) {
        this.visitReport = visitReport;
    }

    public boolean isInternshipId() {
        return internshipId;
    }

    public void setInternshipId(boolean internshipId) {
        this.internshipId = internshipId;
    }
}

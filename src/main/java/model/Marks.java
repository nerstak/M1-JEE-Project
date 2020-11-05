package model;

import java.util.UUID;

public class Marks {
    private UUID marksId;
    private int tech;
    private int communication;
    private UUID internshipId;

    public UUID getMarksId() {
        return marksId;
    }

    public void setMarksId(UUID marksId) {
        this.marksId = marksId;
    }

    public int getTech() {
        return tech;
    }

    public void setTech(int tech) {
        this.tech = tech;
    }

    public int getCommunication() {
        return communication;
    }

    public void setCommunication(int communication) {
        this.communication = communication;
    }

    public UUID getInternshipId() {
        return internshipId;
    }

    public void setInternshipId(UUID internshipId) {
        this.internshipId = internshipId;
    }
}

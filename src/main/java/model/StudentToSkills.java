package model;

import java.util.ArrayList;
import java.util.UUID;

public class StudentToSkills {
    private UUID studentId;
    private ArrayList<Skills> skills;

    public ArrayList<Skills> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<Skills> skills) {
        this.skills = skills;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public void setStudentId(UUID _studentId) {
        studentId = _studentId;
    }
}

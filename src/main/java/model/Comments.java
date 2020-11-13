package model;

import java.util.UUID;

public class Comments {
    private UUID commentsId;
    private String studentComm;
    private String supervisorComment;
    private UUID internshipId;

    public UUID getCommentsId() {
        return commentsId;
    }

    public void setCommentsId(UUID commentsId) {
        this.commentsId = commentsId;
    }

    public String isStudentComm() {
        return studentComm;
    }

    public void setStudentComm(String studentComm) {
        this.studentComm = studentComm;
    }

    public String isSupervisorComment() {
        return supervisorComment;
    }

    public void setSupervisorComment(String supervisorComment) {
        this.supervisorComment = supervisorComment;
    }

    public UUID getInternshipId() {
        return internshipId;
    }

    public void setInternshipId(UUID internshipId) {
        this.internshipId = internshipId;
    }
}

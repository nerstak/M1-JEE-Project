package model;

import java.util.UUID;

public class Comments {
    private UUID commentsId;
    private boolean studentComm;
    private boolean supervisorComment;
    private UUID internshipId;

    public UUID getCommentsId() {
        return commentsId;
    }

    public void setCommentsId(UUID commentsId) {
        this.commentsId = commentsId;
    }

    public boolean isStudentComm() {
        return studentComm;
    }

    public void setStudentComm(boolean studentComm) {
        this.studentComm = studentComm;
    }

    public boolean isSupervisorComment() {
        return supervisorComment;
    }

    public void setSupervisorComment(boolean supervisorComment) {
        this.supervisorComment = supervisorComment;
    }

    public UUID getInternshipId() {
        return internshipId;
    }

    public void setInternshipId(UUID internshipId) {
        this.internshipId = internshipId;
    }
}

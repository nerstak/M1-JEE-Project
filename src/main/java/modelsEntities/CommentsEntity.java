package modelsEntities;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "comments", schema = "public", catalog = "st2eedb")
public class CommentsEntity {
    @Id
    @Column(name = "comments_id", nullable = false, columnDefinition="uuid")
    private UUID commentsId;
    @Basic
    @Column(name = "student_comm", nullable = true, length = -1)
    private String studentComm;

    @Basic
    @Column(name = "supervisor_comm", nullable = true, length = -1)
    private String supervisorComm;
    //private String internshipId;

    @OneToOne
    @JoinColumn( name="internship_id", nullable=true )
    private InternshipEntity internship;

    public UUID getCommentsId() {
        return commentsId;
    }

    public void setCommentsId(UUID commentsId) {
        this.commentsId = commentsId;
    }

    public String getStudentComm() {
        return studentComm;
    }

    public void setStudentComm(String studentComm) {
        this.studentComm = studentComm;
    }

    public String getSupervisorComm() {
        return supervisorComm;
    }

    public void setSupervisorComm(String supervisorComm) {
        this.supervisorComm = supervisorComm;
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

    public InternshipEntity getInternship() {
        return internship;
    }

    public void setInternship(InternshipEntity internship) {
        this.internship = internship;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentsEntity that = (CommentsEntity) o;
        return Objects.equals(commentsId, that.commentsId) &&
                Objects.equals(studentComm, that.studentComm) &&
                Objects.equals(supervisorComm, that.supervisorComm);// &&
//                Objects.equals(internshipId, that.internshipId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentsId, studentComm, supervisorComm);
    }
}

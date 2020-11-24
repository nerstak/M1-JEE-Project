package modelsEntities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "student", schema = "public", catalog = "st2eedb")
public class StudentEntity {
    private String studentId;
    private String name;
    private String firstname;
    private String email;
    private String group;
    private String linkedinProfile;
    private String tutorId;

    @Id
    @Column(name = "student_id", nullable = false)
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Basic
    @Column(name = "name", nullable = true, length = -1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "firstname", nullable = true, length = -1)
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "email", nullable = true, length = -1)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "group", nullable = true, length = -1)
    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Basic
    @Column(name = "linkedin_profile", nullable = true, length = -1)
    public String getLinkedinProfile() {
        return linkedinProfile;
    }

    public void setLinkedinProfile(String linkedinProfile) {
        this.linkedinProfile = linkedinProfile;
    }

    @Basic
    @Column(name = "tutor_id", nullable = true)
    public String getTutorId() {
        return tutorId;
    }

    public void setTutorId(String tutorId) {
        this.tutorId = tutorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentEntity that = (StudentEntity) o;
        return Objects.equals(studentId, that.studentId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(email, that.email) &&
                Objects.equals(group, that.group) &&
                Objects.equals(linkedinProfile, that.linkedinProfile) &&
                Objects.equals(tutorId, that.tutorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, name, firstname, email, group, linkedinProfile, tutorId);
    }
}

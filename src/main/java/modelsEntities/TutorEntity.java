package modelsEntities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tutor", schema = "public", catalog = "st2eedb")
@NamedQueries(
        @NamedQuery(name = "Tutor.SelectSingleTutor", query = "SELECT t FROM TutorEntity t WHERE t.email  = :email AND t.pwd = :pwd")
)
public class TutorEntity {
    private String tutorId;
    private String name;
    private String firstname;
    private String pwd;
    private String email;

    @Id
    @Column(name = "tutor_id", nullable = false)
    public String getTutorId() {
        return tutorId;
    }

    public void setTutorId(String tutorId) {
        this.tutorId = tutorId;
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
    @Column(name = "pwd", nullable = true, length = -1)
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Basic
    @Column(name = "email", nullable = true, length = -1)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TutorEntity that = (TutorEntity) o;
        return Objects.equals(tutorId, that.tutorId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(pwd, that.pwd) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tutorId, name, firstname, pwd, email);
    }
}

package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tutor", schema = "public", catalog = "st2eedb")
@NamedQueries(
        @NamedQuery(name = "Tutor.SelectSingleTutor", query = "SELECT t FROM TutorEntity t WHERE t.email  = :email AND t.pwd = :pwd")
)
public class TutorEntity implements InterfaceEntity {
    @Id
    @Column(name = "tutor_id", nullable = false, columnDefinition="uuid") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID tutorId;
    private String name;
    private String firstname;
    private String pwd;
    private String email;


    public UUID getTutorId() {
        return tutorId;
    }

    public void setTutorId(UUID tutorId) {
        this.tutorId = tutorId;
    }

    @OneToMany(mappedBy="tutorEntity")
    private List<StudentEntity> students = new ArrayList<>();

    public List getListStudents() { return students; }

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

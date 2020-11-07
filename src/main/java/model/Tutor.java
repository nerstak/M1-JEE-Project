package model;

import java.util.UUID;

public class Tutor {
    private UUID tutorId;
    private String name;
    private String firstName;
    private String pwd;
    private String Email;

    public String getEmail() { return Email; }

    public void setEmail(String email) { Email = email; }

    public UUID getTutorId() {
        return tutorId;
    }

    public void setTutorId(UUID tutorId) {
        this.tutorId = tutorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}

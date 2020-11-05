package model;

import java.util.Date;
import java.util.UUID;

public class Internship {
    private UUID intership;
    private String desciption;
    private boolean webSurvey;
    private boolean midInternInfo;
    private Date begining;
    private Date end;
    private String internSupervisor;
    private UUID StudentId;
    private UUID CompanyID;

    public UUID getIntership() {
        return intership;
    }

    public void setIntership(UUID intership) {
        this.intership = intership;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public boolean isWebSurvey() {
        return webSurvey;
    }

    public void setWebSurvey(boolean webSurvey) {
        this.webSurvey = webSurvey;
    }

    public boolean isMidInternInfo() {
        return midInternInfo;
    }

    public void setMidInternInfo(boolean midInternInfo) {
        this.midInternInfo = midInternInfo;
    }

    public Date getBegining() {
        return begining;
    }

    public void setBegining(Date begining) {
        this.begining = begining;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getInternSupervisor() {
        return internSupervisor;
    }

    public void setInternSupervisor(String internSupervisor) {
        this.internSupervisor = internSupervisor;
    }

    public UUID getStudentId() {
        return StudentId;
    }

    public void setStudentId(UUID studentId) {
        StudentId = studentId;
    }

    public UUID getCompanyID() {
        return CompanyID;
    }

    public void setCompanyID(UUID companyID) {
        CompanyID = companyID;
    }
}

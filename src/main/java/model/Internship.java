package model;

import java.util.Date;
import java.util.UUID;

public class Internship {
    private UUID internship;
    private String desciption;
    private boolean webSurvey;
    private boolean midInternInfo;
    private Date begining;
    private Date end;
    private String internSupervisor;
    private UUID StudentId;
    private UUID CompanyID;
    private boolean cdc;
    private boolean companyEval;
    private boolean defense;

    public boolean isCdc() {
        return cdc;
    }

    public void setCdc(boolean cdc) {
        this.cdc = cdc;
    }

    public boolean isCompanyEval() {
        return companyEval;
    }

    public void setCompanyEval(boolean companyEval) {
        this.companyEval = companyEval;
    }

    public boolean isDefense() {
        return defense;
    }

    public void setDefense(boolean defense) {
        this.defense = defense;
    }

    public UUID getInternship() {
        return internship;
    }

    public void setInternship(UUID internship) {
        this.internship = internship;
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

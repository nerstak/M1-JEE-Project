package modelsEntities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "internships_data", schema = "public", catalog = "st2eedb")
public class InternshipsDataEntity {
    private String studentId;
    private String name;
    private String firstname;
    private String email;
    private String studentGroup;
    private String linkedinProfile;
    private String tutorId;
    private String internshipId;
    private String companyId;
    private String description;
    private Boolean midInternInfo;
    private Boolean webSurvey;
    private Date beginning;
    private Date ending;
    private Boolean cdc;
    private Boolean companyEval;
    private Boolean defense;
    private String internSupervisor;
    private String companyName;
    private String address;
    private Boolean done;
    private Boolean planned;
    private Boolean visitReport;
    private String visitId;
    private Integer communication;
    private Integer tech;
    private String marksId;
    private String finalReportId;
    private String title;
    private Boolean report;

    @Basic
    @Column(name = "student_id", nullable = true)
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
    @Column(name = "student_group", nullable = true, length = -1)
    public String getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(String studentGroup) {
        this.studentGroup = studentGroup;
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

    @Basic
    @Column(name = "internship_id", nullable = true)
    public String getInternshipId() {
        return internshipId;
    }

    public void setInternshipId(String internshipId) {
        this.internshipId = internshipId;
    }

    @Basic
    @Column(name = "company_id", nullable = true)
    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    @Basic
    @Column(name = "description", nullable = true, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "mid_intern_info", nullable = true)
    public Boolean getMidInternInfo() {
        return midInternInfo;
    }

    public void setMidInternInfo(Boolean midInternInfo) {
        this.midInternInfo = midInternInfo;
    }

    @Basic
    @Column(name = "web_survey", nullable = true)
    public Boolean getWebSurvey() {
        return webSurvey;
    }

    public void setWebSurvey(Boolean webSurvey) {
        this.webSurvey = webSurvey;
    }

    @Basic
    @Column(name = "beginning", nullable = true)
    public Date getBeginning() {
        return beginning;
    }

    public void setBeginning(Date beginning) {
        this.beginning = beginning;
    }

    @Basic
    @Column(name = "ending", nullable = true)
    public Date getEnding() {
        return ending;
    }

    public void setEnding(Date ending) {
        this.ending = ending;
    }

    @Basic
    @Column(name = "cdc", nullable = true)
    public Boolean getCdc() {
        return cdc;
    }

    public void setCdc(Boolean cdc) {
        this.cdc = cdc;
    }

    @Basic
    @Column(name = "company_eval", nullable = true)
    public Boolean getCompanyEval() {
        return companyEval;
    }

    public void setCompanyEval(Boolean companyEval) {
        this.companyEval = companyEval;
    }

    @Basic
    @Column(name = "defense", nullable = true)
    public Boolean getDefense() {
        return defense;
    }

    public void setDefense(Boolean defense) {
        this.defense = defense;
    }

    @Basic
    @Column(name = "intern_supervisor", nullable = true, length = -1)
    public String getInternSupervisor() {
        return internSupervisor;
    }

    public void setInternSupervisor(String internSupervisor) {
        this.internSupervisor = internSupervisor;
    }

    @Basic
    @Column(name = "company_name", nullable = true, length = -1)
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Basic
    @Column(name = "address", nullable = true, length = -1)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "done", nullable = true)
    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    @Basic
    @Column(name = "planned", nullable = true)
    public Boolean getPlanned() {
        return planned;
    }

    public void setPlanned(Boolean planned) {
        this.planned = planned;
    }

    @Basic
    @Column(name = "visit_report", nullable = true)
    public Boolean getVisitReport() {
        return visitReport;
    }

    public void setVisitReport(Boolean visitReport) {
        this.visitReport = visitReport;
    }

    @Basic
    @Column(name = "visit_id", nullable = true)
    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    @Basic
    @Column(name = "communication", nullable = true)
    public Integer getCommunication() {
        return communication;
    }

    public void setCommunication(Integer communication) {
        this.communication = communication;
    }

    @Basic
    @Column(name = "tech", nullable = true)
    public Integer getTech() {
        return tech;
    }

    public void setTech(Integer tech) {
        this.tech = tech;
    }

    @Basic
    @Column(name = "marks_id", nullable = true)
    public String getMarksId() {
        return marksId;
    }

    public void setMarksId(String marksId) {
        this.marksId = marksId;
    }

    @Basic
    @Column(name = "final_report_id", nullable = true)
    public String getFinalReportId() {
        return finalReportId;
    }

    public void setFinalReportId(String finalReportId) {
        this.finalReportId = finalReportId;
    }

    @Basic
    @Column(name = "title", nullable = true, length = -1)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "report", nullable = true)
    public Boolean getReport() {
        return report;
    }

    public void setReport(Boolean report) {
        this.report = report;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InternshipsDataEntity that = (InternshipsDataEntity) o;
        return Objects.equals(studentId, that.studentId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(email, that.email) &&
                Objects.equals(studentGroup, that.studentGroup) &&
                Objects.equals(linkedinProfile, that.linkedinProfile) &&
                Objects.equals(tutorId, that.tutorId) &&
                Objects.equals(internshipId, that.internshipId) &&
                Objects.equals(companyId, that.companyId) &&
                Objects.equals(description, that.description) &&
                Objects.equals(midInternInfo, that.midInternInfo) &&
                Objects.equals(webSurvey, that.webSurvey) &&
                Objects.equals(beginning, that.beginning) &&
                Objects.equals(ending, that.ending) &&
                Objects.equals(cdc, that.cdc) &&
                Objects.equals(companyEval, that.companyEval) &&
                Objects.equals(defense, that.defense) &&
                Objects.equals(internSupervisor, that.internSupervisor) &&
                Objects.equals(companyName, that.companyName) &&
                Objects.equals(address, that.address) &&
                Objects.equals(done, that.done) &&
                Objects.equals(planned, that.planned) &&
                Objects.equals(visitReport, that.visitReport) &&
                Objects.equals(visitId, that.visitId) &&
                Objects.equals(communication, that.communication) &&
                Objects.equals(tech, that.tech) &&
                Objects.equals(marksId, that.marksId) &&
                Objects.equals(finalReportId, that.finalReportId) &&
                Objects.equals(title, that.title) &&
                Objects.equals(report, that.report);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, name, firstname, email, studentGroup, linkedinProfile, tutorId, internshipId, companyId, description, midInternInfo, webSurvey, beginning, ending, cdc, companyEval, defense, internSupervisor, companyName, address, done, planned, visitReport, visitId, communication, tech, marksId, finalReportId, title, report);
    }
}
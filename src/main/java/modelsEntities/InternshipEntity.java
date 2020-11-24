package modelsEntities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "internship", schema = "public", catalog = "st2eedb")
public class InternshipEntity {
    private String internshipId;
    private String description;
    private Boolean webSurvey;
    private Boolean midInternInfo;
    private Date beginning;
    private Date ending;
    private String studentId;
    private String companyId;
    private Boolean cdc;
    private Boolean companyEval;
    private Boolean defense;
    private String internSupervisor;

    @Id
    @Column(name = "internship_id", nullable = false)
    public String getInternshipId() {
        return internshipId;
    }

    public void setInternshipId(String internshipId) {
        this.internshipId = internshipId;
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
    @Column(name = "web_survey", nullable = true)
    public Boolean getWebSurvey() {
        return webSurvey;
    }

    public void setWebSurvey(Boolean webSurvey) {
        this.webSurvey = webSurvey;
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
    @Column(name = "student_id", nullable = true)
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InternshipEntity that = (InternshipEntity) o;
        return Objects.equals(internshipId, that.internshipId) &&
                Objects.equals(description, that.description) &&
                Objects.equals(webSurvey, that.webSurvey) &&
                Objects.equals(midInternInfo, that.midInternInfo) &&
                Objects.equals(beginning, that.beginning) &&
                Objects.equals(ending, that.ending) &&
                Objects.equals(studentId, that.studentId) &&
                Objects.equals(companyId, that.companyId) &&
                Objects.equals(cdc, that.cdc) &&
                Objects.equals(companyEval, that.companyEval) &&
                Objects.equals(defense, that.defense) &&
                Objects.equals(internSupervisor, that.internSupervisor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(internshipId, description, webSurvey, midInternInfo, beginning, ending, studentId, companyId, cdc, companyEval, defense, internSupervisor);
    }
}

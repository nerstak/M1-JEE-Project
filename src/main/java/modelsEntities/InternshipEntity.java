package modelsEntities;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "internship", schema = "public", catalog = "st2eedb")
@NamedQueries({
        @NamedQuery(name = "Internship.SelectList", query = "SELECT i FROM InternshipEntity i JOIN FETCH i.student s WHERE s.tutorEntity.tutorId = :tutor"),
        @NamedQuery(name = "Internship.SelectSingle", query = "SELECT i FROM InternshipEntity i WHERE i.internshipId = :internshipId")
})
public class InternshipEntity {
    @Id    @Column(name = "internship_id", nullable = false, columnDefinition="uuid") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID internshipId;

    @Basic    @Column(name = "description", nullable = true, length = -1)
    private String description;

    @Basic    @Column(name = "web_survey", nullable = true)
    private Boolean webSurvey;

    @Basic    @Column(name = "mid_intern_info", nullable = true)
    private Boolean midInternInfo;

    @Basic    @Column(name = "beginning", nullable = true)
    private Date beginning;

    @Basic    @Column(name = "ending", nullable = true)
    private Date ending;

//    @Basic    @Column(name = "student_id", nullable = true)
//    private String studentId;
//
//    @Basic    @Column(name = "company_id", nullable = true)
//    private String companyId;

    @Basic    @Column(name = "cdc", nullable = true)
    private Boolean cdc;

    @Basic    @Column(name = "company_eval", nullable = true)
    private Boolean companyEval;

    @Basic    @Column(name = "defense", nullable = true)
    private Boolean defense;

    @Basic    @Column(name = "intern_supervisor", nullable = true, length = -1)
    private String internSupervisor;

    @OneToOne( mappedBy = "internship" )
    private MarksEntity marks;

    @OneToOne( mappedBy = "internship" )
    private CommentsEntity comments;

    @OneToOne( mappedBy = "internship" )
    private FinalReportEntity finalReport;

    @OneToOne( mappedBy = "internship" )
    private VisitEntity visit;

    @ManyToOne  @JoinColumn( name="company_id" )
    private CompanyEntity company;

    @ManyToOne  @JoinColumn( name="student_id" )
    private StudentEntity student;

    @ManyToMany
    @JoinTable( name = "internship_to_keywords",
            joinColumns = @JoinColumn( name = "internship_id" ),
            inverseJoinColumns = @JoinColumn( name = "keyword_id" ) )
    private List<KeywordsEntity> keywords = new ArrayList<>();


    public FinalReportEntity getFinalReport() {return finalReport;}

    public CommentsEntity getComments() {return comments;}

    public MarksEntity getMarks() {return marks;}

    public VisitEntity getVisit() {return visit;}

    public StudentEntity getStudent() {return student;}


    public CompanyEntity getCompany() {return company;}

    public List getListKeywords() {return keywords;}

    public UUID getInternshipId() {
        return internshipId;
    }

    public void setInternshipId(UUID internshipId) {
        this.internshipId = internshipId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getWebSurvey() {
        return webSurvey;
    }

    public void setWebSurvey(Boolean webSurvey) {
        this.webSurvey = webSurvey;
    }


    public Boolean getMidInternInfo() {
        return midInternInfo;
    }

    public void setMidInternInfo(Boolean midInternInfo) {
        this.midInternInfo = midInternInfo;
    }

    public Date getBeginning() {
        return beginning;
    }

    public void setBeginning(Date beginning) {
        this.beginning = beginning;
    }

    public Date getEnding() {
        return ending;
    }

    public void setEnding(Date ending) {
        this.ending = ending;
    }

//    public String getStudentId() {
//        return studentId;
//    }
//
//    public void setStudentId(String studentId) {
//        this.studentId = studentId;
//    }
//
//    public String getCompanyId() {
//        return companyId;
//    }
//
//    public void setCompanyId(String companyId) {
//        this.companyId = companyId;
//    }

    public Boolean getCdc() {
        return cdc;
    }

    public void setCdc(Boolean cdc) {
        this.cdc = cdc;
    }

    public Boolean getCompanyEval() {
        return companyEval;
    }

    public void setCompanyEval(Boolean companyEval) {
        this.companyEval = companyEval;
    }

    public Boolean getDefense() {
        return defense;
    }

    public void setDefense(Boolean defense) {
        this.defense = defense;
    }

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
//                Objects.equals(studentId, that.studentId) &&
//                Objects.equals(companyId, that.companyId) &&
                Objects.equals(cdc, that.cdc) &&
                Objects.equals(companyEval, that.companyEval) &&
                Objects.equals(defense, that.defense) &&
                Objects.equals(internSupervisor, that.internSupervisor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(internshipId, description, webSurvey, midInternInfo, beginning, ending, cdc, companyEval, defense, internSupervisor);
    }
}

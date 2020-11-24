package modelsEntities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "internship_keywords", schema = "public", catalog = "st2eedb")
public class InternshipKeywordsEntity {
    private String keyword;
    private String internshipId;

    @Basic
    @Column(name = "keyword", nullable = true, length = -1)
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Basic
    @Column(name = "internship_id", nullable = true)
    public String getInternshipId() {
        return internshipId;
    }

    public void setInternshipId(String internshipId) {
        this.internshipId = internshipId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InternshipKeywordsEntity that = (InternshipKeywordsEntity) o;
        return Objects.equals(keyword, that.keyword) &&
                Objects.equals(internshipId, that.internshipId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyword, internshipId);
    }
}

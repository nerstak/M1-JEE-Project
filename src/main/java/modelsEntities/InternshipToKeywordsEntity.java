package modelsEntities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "internship_to_keywords", schema = "public", catalog = "st2eedb")
@IdClass(InternshipToKeywordsEntityPK.class)
public class InternshipToKeywordsEntity {
    private String internshipId;
    private String keywordId;

    @Id
    @Column(name = "internship_id", nullable = false)
    public String getInternshipId() {
        return internshipId;
    }

    public void setInternshipId(String internshipId) {
        this.internshipId = internshipId;
    }

    @Id
    @Column(name = "keyword_id", nullable = false)
    public String getKeywordId() {
        return keywordId;
    }

    public void setKeywordId(String keywordId) {
        this.keywordId = keywordId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InternshipToKeywordsEntity that = (InternshipToKeywordsEntity) o;
        return Objects.equals(internshipId, that.internshipId) &&
                Objects.equals(keywordId, that.keywordId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(internshipId, keywordId);
    }
}

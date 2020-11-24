package modelsEntities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class InternshipToKeywordsEntityPK implements Serializable {
    private String internshipId;
    private String keywordId;

    @Column(name = "internship_id", nullable = false)
    @Id
    public String getInternshipId() {
        return internshipId;
    }

    public void setInternshipId(String internshipId) {
        this.internshipId = internshipId;
    }

    @Column(name = "keyword_id", nullable = false)
    @Id
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
        InternshipToKeywordsEntityPK that = (InternshipToKeywordsEntityPK) o;
        return Objects.equals(internshipId, that.internshipId) &&
                Objects.equals(keywordId, that.keywordId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(internshipId, keywordId);
    }
}

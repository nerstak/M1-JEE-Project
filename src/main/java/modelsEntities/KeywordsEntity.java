package modelsEntities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "keywords", schema = "public", catalog = "st2eedb")
public class KeywordsEntity {
    @Id
    @Column(name = "keyword_id", nullable = false)
    private String keywordId;
    private String keyword;


    public String getKeywordId() {
        return keywordId;
    }

    public void setKeywordId(String keywordId) {
        this.keywordId = keywordId;
    }

    @Basic
    @Column(name = "keyword", nullable = true, length = -1)
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @ManyToMany
    @JoinTable( name = "internship_to_keywords",
            joinColumns = @JoinColumn( name = "keyword_id" ),
            inverseJoinColumns = @JoinColumn( name = "internship_id" ) )
    private List<InternshipEntity> internships = new ArrayList<>();

    public List getListInternships() { return internships; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeywordsEntity that = (KeywordsEntity) o;
        return Objects.equals(keywordId, that.keywordId) &&
                Objects.equals(keyword, that.keyword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keywordId, keyword);
    }
}

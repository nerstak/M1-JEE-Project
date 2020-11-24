package modelsEntities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class StudentsToKeywordsEntityPK implements Serializable {
    private String studentId;
    private String keywordId;

    @Column(name = "student_id", nullable = false)
    @Id
    public Object getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
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
        StudentsToKeywordsEntityPK that = (StudentsToKeywordsEntityPK) o;
        return Objects.equals(studentId, that.studentId) &&
                Objects.equals(keywordId, that.keywordId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, keywordId);
    }
}

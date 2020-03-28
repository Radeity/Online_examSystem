package dhu.cst.ExamSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Paper {
  @Id
  @GeneratedValue
  private long paperId;
  @Column
  private String paperName;
  @Column
  private long subjectId;

  public long getPaperId() {
    return paperId;
  }

  public void setPaperId(long paperId) {
    this.paperId = paperId;
  }


  public String getPaperName() {
    return paperName;
  }

  public void setPaperName(String paperName) {
    this.paperName = paperName;
  }


  public long getSubjectId() {
    return subjectId;
  }

  public void setSubjectId(long subjectId) {
    this.subjectId = subjectId;
  }

}

package dhu.cst.ExamSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Compose {
  @Id
  @GeneratedValue
  private long composeId;
  @Column
  private long paperId;
  @Column
  private long teacherId;

  public long getComposeId() {
    return composeId;
  }

  public void setComposeId(long composeId) {
    this.composeId = composeId;
  }

  public long getPaperId() {
    return paperId;
  }

  public void setPaperId(long paperId) {
    this.paperId = paperId;
  }


  public long getTeacherId() {
    return teacherId;
  }

  public void setTeacherId(long teacherId) {
    this.teacherId = teacherId;
  }

}

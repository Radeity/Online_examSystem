package dhu.cst.ExamSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Point {

  @Id
  @GeneratedValue
  private long pointId;
  @Column
  private String pointName;
  @Column
  private long subjectId;


  public long getPointId() {
    return pointId;
  }

  public void setPointId(long pointId) {
    this.pointId = pointId;
  }


  public String getPointName() {
    return pointName;
  }

  public void setPointName(String pointName) {
    this.pointName = pointName;
  }


  public long getSubjectId() {
    return subjectId;
  }

  public void setSubjectId(long subjectId) {
    this.subjectId = subjectId;
  }

}

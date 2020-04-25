package dhu.cst.ExamSystem.entity;

import javax.persistence.*;

@Entity
@Table(name = "point")
public class Point {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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

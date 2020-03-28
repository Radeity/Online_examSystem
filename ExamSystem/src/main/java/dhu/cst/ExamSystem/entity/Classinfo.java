package dhu.cst.ExamSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Classinfo {

  @Id
  @GeneratedValue
  private long classId;
  @Column
  private String className;


  public long getClassId() {
    return classId;
  }

  public void setClassId(long classId) {
    this.classId = classId;
  }


  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

}

package dhu.cst.ExamSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Sc {

  @Id
  @GeneratedValue
  private long id;
  @Column
  private long studentId;
  @Column
  private long classId;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getStudentId() {
    return studentId;
  }

  public void setStudentId(long studentId) {
    this.studentId = studentId;
  }


  public long getClassId() {
    return classId;
  }

  public void setClassId(long classId) {
    this.classId = classId;
  }

}

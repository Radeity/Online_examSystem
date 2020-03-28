package dhu.cst.ExamSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Tc {

  @Id
  @GeneratedValue
  private long id;

  @Column
  private long teacherId;
  @Column
  private long subjectId;
  @Column
  private long classId;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getTeacherId() {
    return teacherId;
  }

  public void setTeacherId(long teacherId) {
    this.teacherId = teacherId;
  }


  public long getSubjectId() {
    return subjectId;
  }

  public void setSubjectId(long subjectId) {
    this.subjectId = subjectId;
  }


  public long getClassId() {
    return classId;
  }

  public void setClassId(long classId) {
    this.classId = classId;
  }

}

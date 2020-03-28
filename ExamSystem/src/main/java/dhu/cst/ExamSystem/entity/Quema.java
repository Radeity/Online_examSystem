package dhu.cst.ExamSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Quema {
  @Id
  @GeneratedValue
  private long quemaId;
  @Column
  private long subjectId;
  @Column
  private long teacherId;

  public long getQuemaId(){
    return quemaId;
  }

  public void setQuemaId(long quemaId){
    this.quemaId = quemaId;
  }

  public long getSubjectId() {
    return subjectId;
  }

  public void setSubjectId(long subjectId) {
    this.subjectId = subjectId;
  }


  public long getTeacherId() {
    return teacherId;
  }

  public void setTeacherId(long teacherId) {
    this.teacherId = teacherId;
  }

}

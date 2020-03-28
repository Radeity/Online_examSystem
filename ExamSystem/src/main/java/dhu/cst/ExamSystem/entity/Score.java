package dhu.cst.ExamSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Score {
  @Id
  @GeneratedValue
  private long scoreId;
  @Column
  private long score;
  @Column
  private long studentId;
  @Column
  private String studentName;
  @Column
  private long subjectId;
  @Column
  private long classId;
  @Column
  private long paperId;
  @Column
  private java.sql.Timestamp submit;


  public long getScoreId() {
    return scoreId;
  }

  public void setScoreId(long scoreId) {
    this.scoreId = scoreId;
  }


  public long getScore() {
    return score;
  }

  public void setScore(long score) {
    this.score = score;
  }


  public long getStudentId() {
    return studentId;
  }

  public void setStudentId(long studentId) {
    this.studentId = studentId;
  }

  public String getStudentName() {
    return studentName;
  }

  public void setStudentName(String studentName) {
    this.studentName = studentName;
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


  public long getPaperId() {
    return paperId;
  }

  public void setPaperId(long paperId) {
    this.paperId = paperId;
  }


  public java.sql.Timestamp getSubmit() {
    return submit;
  }

  public void setSubmit(java.sql.Timestamp submit) {
    this.submit = submit;
  }

}

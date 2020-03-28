package dhu.cst.ExamSystem.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Exam {

  @Id
  @GeneratedValue
  private long examId;
  @Column
  private String examName;
  @Column
  private long paperId;
  @Column
  private long subjectId;
  @Column
  private long singleNum;
  @Column
  private long singleScore;
  @Column
  private long multiNum;
  @Column
  private long multiScore;
  @Column
  private long blankNum;
  @Column
  private long blankScore;
  @Column
  private java.sql.Timestamp deadline;
  @Column
  private long lastTime;


  public long getExamId() {
    return examId;
  }

  public void setExamId(long examId) {
    this.examId = examId;
  }


  public String getExamName() {
    return examName;
  }

  public void setExamName(String examName) {
    this.examName = examName;
  }


  public long getPaperId() {
    return paperId;
  }

  public void setPaperId(long paperId) {
    this.paperId = paperId;
  }


  public long getSubjectId() {
    return subjectId;
  }

  public void setSubjectId(long subjectId) {
    this.subjectId = subjectId;
  }


  public long getSingleNum() {
    return singleNum;
  }

  public void setSingleNum(long singleNum) {
    this.singleNum = singleNum;
  }


  public long getSingleScore() {
    return singleScore;
  }

  public void setSingleScore(long singleScore) {
    this.singleScore = singleScore;
  }


  public long getMultiNum() {
    return multiNum;
  }

  public void setMultiNum(long multiNum) {
    this.multiNum = multiNum;
  }


  public long getMultiScore() {
    return multiScore;
  }

  public void setMultiScore(long multiScore) {
    this.multiScore = multiScore;
  }


  public long getBlankNum() {
    return blankNum;
  }

  public void setBlankNum(long blankNum) {
    this.blankNum = blankNum;
  }


  public long getBlankScore() {
    return blankScore;
  }

  public void setBlankScore(long blankScore) {
    this.blankScore = blankScore;
  }


  public java.sql.Timestamp getDeadline() {
    return deadline;
  }

  public void setDeadline(java.sql.Timestamp deadline) {
    this.deadline = deadline;
  }


  public long getLastTime() {
    return lastTime;
  }

  public void setLastTime(long lastTime) {
    this.lastTime = lastTime;
  }

}

package dhu.cst.ExamSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Answer {
  @Id
  @GeneratedValue
  private long answerId;
  @Column
  private long studentId;
  @Column
  private long paperId;
  @Column
  private long questionId;
  @Column
  private String stuAnswer;
  @Column
  private String answerCheck;
  @Column
  private String corAnswer;

  public long getAnswerId() {
    return answerId;
  }

  public void setAnswerId(long answerId) {
    this.answerId = answerId;
  }

  public long getStudentId() {
    return studentId;
  }

  public void setStudentId(long studentId) {
    this.studentId = studentId;
  }


  public long getPaperId() {
    return paperId;
  }

  public void setPaperId(long paperId) {
    this.paperId = paperId;
  }


  public long getQuestionId() {
    return questionId;
  }

  public void setQuestionId(long questionId) {
    this.questionId = questionId;
  }


  public String getStuAnswer() {
    return stuAnswer;
  }

  public void setStuAnswer(String stuAnswer) {
    this.stuAnswer = stuAnswer;
  }

  public String getAnswerCheck() {
    return answerCheck;
  }

  public void setAnswerCheck(String answerCheck) {
    this.answerCheck = answerCheck;
  }

  public String getCorAnswer() {
    return corAnswer;
  }

  public void setCorAnswer(String corAnswer) {
    this.corAnswer = corAnswer;
  }

}

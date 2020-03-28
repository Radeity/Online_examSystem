package dhu.cst.ExamSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Question {
  @Id
  @GeneratedValue
  private long questionId;
  @Column
  private String questionName;
  @Column
  private long questionType;
  @Column
  private String opt;
  @Column
  private String questionAnswer;
  @Column
  private long subjectId;
  @Column
  private long pointId;


  public long getQuestionId() {
    return questionId;
  }

  public void setQuestionId(long questionId) {
    this.questionId = questionId;
  }


  public String getQuestionName() {
    return questionName;
  }

  public void setQuestionName(String questionName) {
    this.questionName = questionName;
  }


  public long getQuestionType() {
    return questionType;
  }

  public void setQuestionType(long questionType) {
    this.questionType = questionType;
  }


  public String getOpt() {
    return opt;
  }

  public void setOpt(String opt) {
    this.opt = opt;
  }


  public String getQuestionAnswer() {
    return questionAnswer;
  }

  public void setQuestionAnswer(String questionAnswer) {
    this.questionAnswer = questionAnswer;
  }


  public long getSubjectId() {
    return subjectId;
  }

  public void setSubjectId(long subjectId) {
    this.subjectId = subjectId;
  }


  public long getPointId() {
    return pointId;
  }

  public void setPointId(long pointId) {
    this.pointId = pointId;
  }

}

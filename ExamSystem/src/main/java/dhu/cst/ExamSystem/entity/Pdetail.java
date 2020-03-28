package dhu.cst.ExamSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Pdetail {
  @Id
  @GeneratedValue
  private long detailId;
  @Column
  private long paperId;
  @Column
  private long questionId;
  @Column
  private long questionType;
  @Column
  private String opt;
  @Column
  private String questionName;
  @Column
  private String corAnswer;


  public long getDetailId() {
    return detailId;
  }

  public void setDetailId(long detailId) {
    this.detailId = detailId;
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

  public String getQuestionName() {
    return questionName;
  }

  public void setQuestionName(String questionName) {
    this.questionName = questionName;
  }


  public String getCorAnswer() {
    return corAnswer;
  }

  public void setCorAnswer(String corAnswer) {
    this.corAnswer = corAnswer;
  }

}

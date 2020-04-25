package dhu.cst.ExamSystem.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "exam")
public class Exam {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long examId;
  @Column
  private String examName;
  @Column
  private long paperId;
  @Column
  private long subjectId;
  @Column
  private String subjectName;
  @Column
  private java.sql.Timestamp deadline;
  @Column
  private long lastTime;
  @Column
  private String state;
  @Column
  private String paperName;

  @ManyToMany(cascade = {CascadeType.PERSIST})//级联类型保存
  @JoinTable(name="ec",
          joinColumns = {@JoinColumn(name = "exam_id")},
          inverseJoinColumns = {@JoinColumn(name = "class_id")})
  private Set<Classinfo> classset= new HashSet<Classinfo>(0);

  public Set<Classinfo> getClassset() {
    return classset;
  }

  public void setClassset(Set<Classinfo> classset) {
    this.classset = classset;
  }

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

  public String getSubjectName() {
    return subjectName;
  }

  public void setSubjectName(String subjectName) {
    this.subjectName = subjectName;
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

  public String getState(){return state;}

  public void setState(String state){this.state = state;}

}

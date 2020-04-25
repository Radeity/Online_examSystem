package dhu.cst.ExamSystem.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table
public class Answer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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
  @Column
  private long answerPoint;
}

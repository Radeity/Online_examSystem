package dhu.cst.ExamSystem.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "question")
public class Question {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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
  @Column
  private String bankName;

}

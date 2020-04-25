package dhu.cst.ExamSystem.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "pdetail")
public class Pdetail {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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
  @Column
  private long pointId;

}

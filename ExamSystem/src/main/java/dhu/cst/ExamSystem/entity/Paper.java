package dhu.cst.ExamSystem.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "paper")
public class Paper {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long paperId;
  @Column
  private String paperName;
  @Column
  private long subjectId;
  @Column
  private String subjectName;
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

}

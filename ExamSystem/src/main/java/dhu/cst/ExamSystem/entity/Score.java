package dhu.cst.ExamSystem.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "score")
public class Score {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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
  private String subjectName;
  @Column
  private long classId;
  @Column
  private String className;
  @Column
  private String teacherName;
  @Column
  private long paperId;
  @Column
  private java.sql.Timestamp submit;
  @Column
  private long examId;
  @Column
  private String examName;

}

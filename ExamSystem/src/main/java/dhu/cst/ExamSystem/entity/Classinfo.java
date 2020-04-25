package dhu.cst.ExamSystem.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "classinfo")
public class Classinfo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long classId;
  @Column
  private String className;
  @Column
  private long teacherId;
  @Column
  private long subjectId;

}

package dhu.cst.ExamSystem.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Subject {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long subjectId;
  @Column
  private String subjectName;
}

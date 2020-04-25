package dhu.cst.ExamSystem.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
@Data
public class Teacherview {
    @Id
    private long teacherId;
    @Column
    private String teacherName;
    @Column
    private long subjectId;

}

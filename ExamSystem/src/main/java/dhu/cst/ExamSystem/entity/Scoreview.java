package dhu.cst.ExamSystem.entity;

import lombok.Data;

import javax.persistence.*;

@Table
@Entity
@Data
public class Scoreview {
    @Id
    private long scoreId;
    @Column
    private long score;
    @Column
    private long studentId;
    @Column
    private String studentNo;
    @Column
    private String studentName;
    @Column
    private String adminclass;
    @Column
    private String gender;
    @Column
    private String department;
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

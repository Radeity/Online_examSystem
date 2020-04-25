package dhu.cst.ExamSystem.entity;

import dhu.cst.ExamSystem.common.PrimaryKey;

import javax.persistence.*;

@IdClass(PrimaryKey.class)
@Entity
@Table(name = "ec")
public class Ec {
    @Id
    @Column(name = "exam_id", nullable = false)
    private long examId;
    @Id
    @Column(name = "class_id", nullable = false)
    private long classId;

    public long getExamId() {
        return examId;
    }

    public void setExamId(long examId) {
        this.examId = examId;
    }

    public long getClassId() {
        return classId;
    }

    public void setClassId(long classId) {
        this.classId = classId;
    }
}

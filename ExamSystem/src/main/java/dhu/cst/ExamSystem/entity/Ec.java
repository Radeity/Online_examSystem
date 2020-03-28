package dhu.cst.ExamSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Ec {
    @Id
    @GeneratedValue
    private long Id;
    @Column
    private long examId;
    @Column
    private long classId;

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

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

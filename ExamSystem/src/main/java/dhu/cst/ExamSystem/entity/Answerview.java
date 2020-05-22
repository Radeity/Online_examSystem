package dhu.cst.ExamSystem.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
@Data
public class Answerview {
    @Id
    private long answerId;
    @Column
    private long paperId;
    @Column
    private long studentId;
    @Column
    private long questionId;
    @Column
    private long questionType;
    @Column
    private String opt;
    @Column
    private String questionName;
    @Column
    private String stuAnswer;
    @Column
    private String answerCheck;
    @Column
    private String corAnswer;
    @Column
    private long answerPoint;
    @Column
    private int favorite;
    @Column
    private String bankName;
}

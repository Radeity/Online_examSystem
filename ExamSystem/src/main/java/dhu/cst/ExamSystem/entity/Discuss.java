package dhu.cst.ExamSystem.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table
public class Discuss {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long discussId;
    @Column
    private long stuId;
    @Column
    private String stuName;
    @Column
    private long questionId;
    @Column
    private int reply;
    @Column
    private long replydiscuss;
    @Column
    private long replystuid;
    @Column
    private String replystuname;
    @Column
    private String content;
    @Column
    private java.sql.Timestamp dtime;
    @Column
    private long thumb;
}

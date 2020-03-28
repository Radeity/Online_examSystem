package dhu.cst.ExamSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Student implements Serializable {

  @Id
  @GeneratedValue
  private long studentId;
  @Column
  private String studentNo;
  @Column
  private String studentName;
  @Column
  private String studentPwd;


  public long getStudentId() {
    return studentId;
  }

  public void setStudentId(long studentId) {
    this.studentId = studentId;
  }


  public String getStudentNo() {
    return studentNo;
  }

  public void setStudentNo(String studentNo) {
    this.studentNo = studentNo;
  }


  public String getStudentName() {
    return studentName;
  }

  public void setStudentName(String studentName) {
    this.studentName = studentName;
  }


  public String getStudentPwd() {
    return studentPwd;
  }

  public void setStudentPwd(String studentPwd) {
    this.studentPwd = studentPwd;
  }

}

package dhu.cst.ExamSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Teacher implements Serializable {

  @Id
  @GeneratedValue
  private long teacherId;
  @Column
  private String teacherNo;
  @Column
  private String teacherName;
  @Column
  private String teacherPwd;


  public long getTeacherId() {
    return teacherId;
  }

  public void setTeacherId(long teacherId) {
    this.teacherId = teacherId;
  }


  public String getTeacherNo() {
    return teacherNo;
  }

  public void setTeacherNo(String teacherNo) {
    this.teacherNo = teacherNo;
  }


  public String getTeacherName() {
    return teacherName;
  }

  public void setTeacherName(String teacherName) {
    this.teacherName = teacherName;
  }


  public String getTeacherPwd() {
    return teacherPwd;
  }

  public void setTeacherPwd(String teacherPwd) {
    this.teacherPwd = teacherPwd;
  }

}

package dhu.cst.ExamSystem.service;

import dhu.cst.ExamSystem.dao.StuRepository;
import dhu.cst.ExamSystem.dao.TeaRepository;
import dhu.cst.ExamSystem.entity.Student;
import dhu.cst.ExamSystem.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChangePassService {
    @Autowired
    private StuRepository stuRepository;
    @Autowired
    private TeaRepository teaRepository;

    public boolean stuchangePass(String username,String oldpassword,String newpassword){
        List<Student> stu = stuRepository.findByStudentNoAndStudentPwd(username,oldpassword);
        if(stu.size()!=0){
            Student student = stu.get(0);
            student.setStudentPwd(newpassword);
            stuRepository.save(student);
            return true;
        }
        else return false;
    }

    public boolean teachangePass(String username,String oldpassword,String newpassword){
        List<Teacher> tea = teaRepository.findByTeacherNoAndTeacherPwd(username,oldpassword);
        if(tea.size()!=0){
            Teacher teacher = tea.get(0);
            teacher.setTeacherPwd(newpassword);
            teaRepository.save(teacher);
            return true;
        }
        else return false;
    }
}

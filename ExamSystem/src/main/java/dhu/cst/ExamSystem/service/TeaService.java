package dhu.cst.ExamSystem.service;

import dhu.cst.ExamSystem.dao.ExamRepository;
import dhu.cst.ExamSystem.dao.TCRepository;
import dhu.cst.ExamSystem.dao.TeaRepository;
import dhu.cst.ExamSystem.entity.Exam;
import dhu.cst.ExamSystem.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeaService {
    @Autowired
    private TeaRepository teaRepository;
    @Autowired
    private TCRepository tcRepository;
    @Autowired
    private ExamRepository examRepository;

    /*public Map login(String username , String password){
        Map m = new HashMap();
        List<Teacher> tea = teaRepository.findByTeacherNoAndTeacherPwd(username,password);
        if(tea.size()!=0){
            Integer admin=1;
            String token = UUID.randomUUID().toString();

            return m;
        }
        else {
            m.put("error","Login failed!");
            return m;
        }
    }*/

    public Map findsubject(long teacherid){
        Map m = new HashMap();
        List<Subject> subjects = tcRepository.findSubjectName(teacherid);
        m.put("subject",subjects);
        m.put("msg","Load successfully!");
        return m;
    }

    public Map getExamdetails(long examid){
        Map m = new HashMap();
        List<Exam> exam = examRepository.findByExamId(examid);
        m.put("exam",exam);
        return m;
    }

    public Map getExamlist(long teacherid){
        Map m = new HashMap();
        List<Exam> exam = examRepository.queryExamList(teacherid);
        m.put("exam",exam);
        return m;
    }


    public String logout(HttpServletRequest request) {
    /*    String token = request.getHeader("token");
        Boolean delete = redisService.delete(token);
        if (!delete) {
            return "注销失败，请检查是否登录！";
        }
        return "注销成功！";*/
        return "unfinished!";
    }
}

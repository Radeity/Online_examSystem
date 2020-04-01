package dhu.cst.ExamSystem.controller;

import com.alibaba.fastjson.JSON;
import dhu.cst.ExamSystem.entity.Answer;
import dhu.cst.ExamSystem.entity.Exam;
import dhu.cst.ExamSystem.entity.Score;
import dhu.cst.ExamSystem.service.ExamService;
import dhu.cst.ExamSystem.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
@PreAuthorize("hasAnyRole('ROLE_STU')")
public class StuExamController {
    @Autowired
    private ExamService examservice;
    @Autowired
    private GradeService gradeservice;

    @RequestMapping(value = "/findExam")
    public List<Exam> findExam(@RequestParam("ExamName") String ExamName,@RequestParam("StudentId") long studentId){
        return examservice.findExam(ExamName,studentId);
    }

    @RequestMapping(value = "/getExam")
    public List<Exam> getExam(@RequestParam("StudentId") long StudentId){
        return examservice.getExam(StudentId);
    }

    @RequestMapping(value = "/getPaper")
    public Map getPaper(@RequestParam("ExamId") long ExamId) {
        return examservice.getPaper(ExamId);
    }

    @RequestMapping(value = "/submit",method = {RequestMethod.POST})
    public Map submit(@RequestBody String str) {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        List<Answer> answer = JSON.parseArray(JSON.parseObject(str).getString("answer"), Answer.class);
        return examservice.submit(time,answer);
    }

    @RequestMapping(value = "/checkstate")
    public Map personalExamstate(@RequestParam("StudentId") long StudentId,@RequestParam("ExamId") long ExamId) {
        return examservice.examstate(StudentId,ExamId);
    }

    @RequestMapping(value = "/getgrade")
    public List<Score> personalExamstate(@RequestParam("Studentname") String name) {
        return gradeservice.findgradebyname(name);
    }

    @RequestMapping(value = "/paperdetail")
    public Map paperdetail(@RequestParam("StudentId") long StudentId,@RequestParam("ExamId") long ExamId){
        return examservice.paperdetail(StudentId,ExamId);
    }
}

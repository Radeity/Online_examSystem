package dhu.cst.ExamSystem.controller;

import com.alibaba.fastjson.JSON;
import dhu.cst.ExamSystem.entity.Answer;
<<<<<<< HEAD
import dhu.cst.ExamSystem.entity.Exam;
import dhu.cst.ExamSystem.entity.Score;
import dhu.cst.ExamSystem.service.ExamService;
import dhu.cst.ExamSystem.service.GradeService;
=======
import dhu.cst.ExamSystem.entity.Scoreview;
import dhu.cst.ExamSystem.service.IExamService;
import dhu.cst.ExamSystem.service.IGradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
>>>>>>> dev
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Api(tags = "学生接口")
@RestController
@RequestMapping("/api")
@PreAuthorize("hasAnyRole('ROLE_STU')")
public class StuExamController {
    @Autowired
<<<<<<< HEAD
    private ExamService examservice;
    @Autowired
    private GradeService gradeservice;
=======
    private IExamService examservice;
    @Autowired
    private IGradeService gradeservice;

>>>>>>> dev

    @ApiOperation("根据考试名字寻找考试")
    @GetMapping(value = "/findExam")
    public Map findExam(@RequestParam("ExamName") String ExamName){
        return examservice.findExam(ExamName);
    }

    @ApiOperation("获取当前学生考试信息")
    @GetMapping(value = "/getExam")
    public Map getExam(){
        return examservice.getExam();
    }

    @ApiOperation("学生获取试卷")
    @GetMapping(value = "/getPaper")
    public Map getPaper(@RequestParam("ExamId") long ExamId) {
        return examservice.getPaper(ExamId);
    }

    @ApiOperation("学生提交试卷")
    @PostMapping(value = "/submit")
    public Map submit(@RequestBody String str) {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        List<Answer> answer = JSON.parseArray(JSON.parseObject(str).getString("answer"), Answer.class);
        return examservice.submit(time,answer);
    }

    @ApiOperation("获取成绩信息")
    @GetMapping(value = "/getgrade")
    public List<Scoreview> personalExamstate() {
        return gradeservice.stufindgrade();
    }

<<<<<<< HEAD
    @RequestMapping(value = "/getgrade")
    public List<Score> personalExamstate(@RequestParam("Studentname") String name) {
        return gradeservice.findgradebyname(name);
    }

    @RequestMapping(value = "/paperdetail")
    public Map paperdetail(@RequestParam("StudentId") long StudentId,@RequestParam("ExamId") long ExamId){
        return examservice.paperdetail(StudentId,ExamId);
=======
    @ApiOperation("获取成绩详情")
    @GetMapping(value = "/paperdetail")
    public Map paperdetail(@RequestParam("ExamId") long ExamId){
        return examservice.paperdetail(ExamId);
>>>>>>> dev
    }
}

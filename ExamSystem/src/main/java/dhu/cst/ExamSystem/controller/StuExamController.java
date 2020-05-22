package dhu.cst.ExamSystem.controller;

import com.alibaba.fastjson.JSON;
import dhu.cst.ExamSystem.entity.Answer;
import dhu.cst.ExamSystem.entity.Scoreview;
import dhu.cst.ExamSystem.service.IExamService;
import dhu.cst.ExamSystem.service.IGradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    private IExamService examservice;
    @Autowired
    private IGradeService gradeservice;


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

    @ApiOperation("获取成绩详情")
    @GetMapping(value = "/paperdetail")
    public Map paperdetail(@RequestParam("ExamId") long ExamId){
        return examservice.paperdetail(ExamId);
    }

    @ApiOperation("获取收藏题目")
    @GetMapping(value = "/getfavorite")
    public Map getfavorite(){
        return examservice.getfavorite();
    }

    @ApiOperation("收藏")
    @PostMapping(value = "/favorite")
    public boolean favorite(@RequestParam("QuestionId") long questionId){
        return examservice.favorite(questionId);
    }

    @ApiOperation("取消收藏")
    @PostMapping(value = "/unfavorite")
    public boolean unfavorite(@RequestParam("QuestionId") long questionId){
        return examservice.unfavorite(questionId);
    }

}

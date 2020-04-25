package dhu.cst.ExamSystem.controller;

import dhu.cst.ExamSystem.entity.Classinfo;
import dhu.cst.ExamSystem.entity.Exam;
import dhu.cst.ExamSystem.entity.Subject;
import dhu.cst.ExamSystem.entity.Teacherview;
import dhu.cst.ExamSystem.service.IExamsetService;
import dhu.cst.ExamSystem.service.ITeaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
=======
import org.springframework.web.bind.annotation.*;
>>>>>>> dev

import java.util.List;
import java.util.Map;

@Api(tags = "老师与考试相关的接口")
@RestController
@RequestMapping("/tea")
@PreAuthorize("hasAnyRole('ROLE_TEA')")
public class ExamTeaController {
    @Autowired
    private IExamsetService examsetservice;
    @Autowired
    private ITeaService teaService;

    @ApiOperation("根据学科查找老师")
    @GetMapping(value="/teacherquery")
    public List<Teacherview> teacherQuery(@RequestParam("SubjectId") long subjectid){
        return teaService.teacherQuery(subjectid);
    }

    @ApiOperation("根据学科查找班级")
    @GetMapping(value="/classquery")
    public List<Classinfo> classQuery(@RequestParam("SubjectId") long subjectid){
        return examsetservice.classQuery(subjectid);
    }

    @ApiOperation("插入或更新考试信息")
    @PostMapping(value = "/saveorupdateExam")
    public Map regExam(@RequestBody String exam){
        return examsetservice.regExam(exam);
    }

    @ApiOperation("删除考试信息")
    @DeleteMapping(value = "/delExam")
    public Map delExam(@RequestParam("ExamId")long examId){
        return examsetservice.delexam(examId);
    }

    @ApiOperation("获取考试的详细信息")
    @GetMapping(value = "/getexamdetails")
    public Map getExamdetail(@RequestParam("ExamId") long examid ){
        return teaService.getExamdetails(examid);
    }

    @ApiOperation("获取当前有的考试列表")
    @GetMapping(value = "/getexamlist")
    public List<Exam> getExamlist(@RequestParam("SubjectId") long subjectid){
        return teaService.getExamlist(subjectid);
    }

    @ApiOperation("根据名字查找考试")
    @GetMapping(value = "/teafindexam")
    public List<Exam> findExam(@RequestParam("ExamName") String ExamName){
        return examsetservice.findExam(ExamName);
    }

    @ApiOperation("获取当前教师教授的学科")
    @GetMapping(value = "/getsubject")
    public List<Subject> getSubject(){
        return teaService.findsubject();
    }

    @ApiOperation("发布考试")
    @PostMapping(value = "/publishexam")
    public boolean publish(@RequestParam("ExamId") long examid){
        return examsetservice.publish(examid);
    }

    @ApiOperation("撤销发布考试")
    @PostMapping(value = "/unpublishexam")
    public boolean unpublish(@RequestParam("ExamId") long examid){
        return examsetservice.unpublish(examid);
    }


    @ApiOperation("获取已发布考试")
    @GetMapping(value = "/getpublishedexam")
    public List<Exam> getpublishexam(){
        return teaService.getpublishExam();
    }

}

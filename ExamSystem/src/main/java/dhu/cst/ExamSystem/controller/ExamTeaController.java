package dhu.cst.ExamSystem.controller;

import dhu.cst.ExamSystem.entity.Exam;
import dhu.cst.ExamSystem.service.ExamsetService;
import dhu.cst.ExamSystem.service.TeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tea")
@PreAuthorize("hasAnyRole('ROLE_TEA')")
public class ExamTeaController {
    @Autowired
    private ExamsetService examsetservice;
    @Autowired
    private TeaService teaService;

    @RequestMapping(value="/classquery")
    public Map classQuery(@RequestParam ("TeacherId") long TeacherId){
        return examsetservice.classQuery(TeacherId);
    }

    @RequestMapping(value = "/saveorupdateExam")
    public Map regExam(@RequestBody String exam){
        return examsetservice.regExam(exam);
    }

    @RequestMapping(value = "/delExam")
    public Map delExam(@RequestParam("ExamId")long examId){
        return examsetservice.delexam(examId);
    }

    @RequestMapping(value = "/getexamdetails")
    public Map getExamdetail(@RequestParam("ExamId") long examid ){
        return teaService.getExamdetails(examid);
    }

    @RequestMapping(value = "/getexamlist")
    public Map getExamlist(@RequestParam("TeacherId") long teacherid ){
        return teaService.getExamlist(teacherid);
    }

    @RequestMapping(value = "/teafindexam")
    public List<Exam> findExam(@RequestParam("ExamName") String ExamName,@RequestParam ("TeacherId") long TeacherId){
        return examsetservice.findExam(ExamName,TeacherId);
    }

    @RequestMapping(value = "/getsubject")
    public Map getSubject(@RequestParam ("TeacherId") long TeacherId){
        return teaService.findsubject(TeacherId);
    }

}

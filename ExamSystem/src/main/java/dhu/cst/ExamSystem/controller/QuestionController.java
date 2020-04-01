package dhu.cst.ExamSystem.controller;

import dhu.cst.ExamSystem.entity.Question;
import dhu.cst.ExamSystem.service.QuestionService;
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
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @RequestMapping(value = "/getquestion")
    public List<Question> getquestion(@RequestParam("TeacherId") long teacherId) {
        return questionService.getquestion(teacherId);
    }

    @RequestMapping(value = "/addquetopaper")
    public Map addquestiontoexam(@RequestParam("questionId") long questionId, @RequestParam("paperId") long paperId) {
        return questionService.addquestiontoexam(questionId, paperId);
    }

    @RequestMapping(value = "/getquestionnum")
    public Map getquestionnum(@RequestParam("paperId") long paperId) {
        return questionService.getquestionnum(paperId);
    }

    @RequestMapping(value = "/delequefrompaper")
    public Map deletequefromexam(@RequestParam("questionId") long questionId, @RequestParam("paperId") long paperId){
        return questionService.delquefromexam(questionId,paperId);
    }

    @RequestMapping(value = "/saveorupdatequestion")
    public Map addquestion(@RequestBody String strque) {
        return questionService.addquestion(strque);
    }

    @RequestMapping(value = "/delequestion")
    public Map deletequestion(@RequestParam("questionId") long questionId){
        return questionService.delquestion(questionId);
    }

}

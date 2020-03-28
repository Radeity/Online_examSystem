package dhu.cst.ExamSystem.controller;

import dhu.cst.ExamSystem.entity.Answer;
import dhu.cst.ExamSystem.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AnswerController {
    @Autowired
    AnswerService answerService;

    @RequestMapping(value = "/getanswerbypaper")
    public List<Answer> getanswerbypaperm(@RequestParam("PaperId") long paperid){
        return answerService.getanswerbypaper(paperid);
    }


}

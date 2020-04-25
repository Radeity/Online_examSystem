package dhu.cst.ExamSystem.controller;

import dhu.cst.ExamSystem.entity.Answer;
import dhu.cst.ExamSystem.service.IAnswerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
<<<<<<< HEAD
=======
import org.springframework.web.bind.annotation.GetMapping;
>>>>>>> dev
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "这个接口也许它没什么用")
@RestController
@RequestMapping("/tea")
@PreAuthorize("hasAnyRole('ROLE_TEA')")
public class AnswerController {
    @Autowired
    IAnswerService answerService;

    @ApiOperation("根据paperId获取答案")
    @GetMapping(value = "/getanswerbypaper")
    public List<Answer> getanswerbypaperm(@RequestParam("PaperId") long paperid){
        return answerService.getanswerbypaper(paperid);
    }
}

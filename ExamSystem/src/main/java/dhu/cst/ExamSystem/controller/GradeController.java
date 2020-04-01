package dhu.cst.ExamSystem.controller;

import dhu.cst.ExamSystem.entity.Classinfo;
import dhu.cst.ExamSystem.entity.Score;
import dhu.cst.ExamSystem.entity.Subject;
import dhu.cst.ExamSystem.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tea")
@PreAuthorize("hasAnyRole('ROLE_TEA')")
public class GradeController {
    @Autowired
    GradeService gradeService;

    @RequestMapping(value="/teafindclass")
    public List<Classinfo> classQuery(@RequestParam("TeacherId") long TeacherId){
        return gradeService.classQuery(TeacherId);
    }

    @RequestMapping(value="/teafindsubject")
    public List<Subject> subjectQuery(@RequestParam("TeacherId") long TeacherId){
        return gradeService.subjectQuery(TeacherId);
    }

    @RequestMapping(value="/findgradebyname")
    public List<Score> findgradebyname(@RequestParam(value = "StudentName") String studentName){
        if(studentName!="0"){return gradeService.findgradebyname(studentName);}
        return null;
    }

    @RequestMapping(value="/findgradebysubject")
    public List<Score> findgradebysubject(@RequestParam(value = "subjectId") long subjectId){
        return gradeService.findgradebysubject(subjectId);
    }

    @RequestMapping(value="/findgradebyclass")
    public List<Score> findgradebyclass(@RequestParam(value = "classId") long classId){
        return gradeService.findgradebyclass(classId);
    }
}

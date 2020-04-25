package dhu.cst.ExamSystem.controller;

import dhu.cst.ExamSystem.entity.Scoreview;
import dhu.cst.ExamSystem.service.IExamService;
import dhu.cst.ExamSystem.service.IExamsetService;
import dhu.cst.ExamSystem.service.IGradeService;
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
import java.util.Map;

@Api(tags = "老师与成绩相关接口")
@RestController
@RequestMapping("/tea")
@PreAuthorize("hasAnyRole('ROLE_TEA')")
public class GradeController {
    @Autowired
    IGradeService gradeService;
    @Autowired
    IExamsetService examsetService;
    @Autowired
    IExamService examService;

    @ApiOperation("根据姓名查找成绩")
    @GetMapping(value="/findgradebyname")
    public List<Scoreview> findgradebyname(@RequestParam(value = "StudentName") String studentName){
        if(studentName!="0"){return gradeService.findgradebyname(studentName);}
        return null;
    }

    @ApiOperation("根据老师查找成绩")
    @GetMapping(value="/getallgrade")
    public List<Scoreview> findgradebyteacher(@RequestParam("pageNum") Integer page, @RequestParam("pageSize") Integer size){
        return gradeService.findgradebyteacher(page,size);
    }

    @ApiOperation("根据学科查找成绩")
    @GetMapping(value="/findgradebysubject")
    public List<Scoreview> findgradebysubject(@RequestParam(value = "subjectId") long subjectId,
                                              @RequestParam("pageNum") Integer page, @RequestParam("pageSize") Integer size){
        return gradeService.findgradebysubject(subjectId,page,size);
    }

    @ApiOperation("老师查看某一个学生某一个考试的试卷详情")
    @GetMapping(value = "/paperdetailfortea")
    public Map paperdetail(@RequestParam("StudentId") long StudentId,@RequestParam("ExamId") long ExamId){
        return examService.paperdetail(StudentId,ExamId);
    }

    @ApiOperation("成绩综合查询")
    @GetMapping(value="/gradeintegratedquery")
    public List<Scoreview> integratedquery(@RequestParam(value = "subjectName",required = false,defaultValue = "") String subjectName,
                                           @RequestParam(value = "examName",required = false,defaultValue = "") String examName,
                                           @RequestParam(value = "teacherName",required = false,defaultValue = "") String teacherName,
                                           @RequestParam("pageNum") Integer page,
                                           @RequestParam("pageSize") Integer size) {
        return gradeService.integratedquery(subjectName,examName,teacherName,page,size);
    }


    @ApiOperation("根据班级查找成绩")
    @GetMapping(value="/findgradebyclass")
    public List<Scoreview> findgradebyclass(@RequestParam(value = "classId") long classId,
                                        @RequestParam("pageNum") Integer page,
                                        @RequestParam("pageSize") Integer size){
        return gradeService.findgradebyclass(classId,page,size);
    }
}

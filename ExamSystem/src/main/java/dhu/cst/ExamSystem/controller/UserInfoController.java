package dhu.cst.ExamSystem.controller;

import dhu.cst.ExamSystem.common.StuInfoResult;
import dhu.cst.ExamSystem.entity.Student;
import dhu.cst.ExamSystem.entity.Teacher;
import dhu.cst.ExamSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserInfoController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/getstufortea")
    public StuInfoResult getstufortea(@RequestParam("StudentId") long studentid){
        return userService.getstufortea(studentid);
    }

    @RequestMapping(value = "/findstuforadmin")
    public List<Student> getstuforadmin(@RequestParam(value = "StudentNo",required = false,defaultValue = "0") long studentNo,
                                        @RequestParam(value = "StudentName",required = false,defaultValue = "0") String studentName){
        if(studentNo!=0){return userService.findstuforadmin(studentNo);}
        if(studentName!="0"){return userService.findstuforadmin(studentName);}
        return null;
    }

    @RequestMapping(value = "/getstuforadmin")
    public List<Student> getstuforadmin(){
        return userService.getstuforadmin();
    }

    @RequestMapping(value = "/saveorupdatestu")
    public Map addstu(@RequestBody String strstu) {
        return userService.addstu(strstu);
    }

    @RequestMapping(value = "/delestu")
    public Map deletestu(@RequestParam("studentId") long studentId){
        return userService.delstu(studentId);
    }

    @RequestMapping(value = "/findteaforadmin")
    public List<Teacher> getteaforadmin(@RequestParam(value = "TeacherNo",required = false,defaultValue = "0") long teacherNo,
                                        @RequestParam(value = "TeachertName",required = false,defaultValue = "0") String teacherName){
        if(teacherNo!=0){return userService.findteaforadmin(teacherNo);}
        if(teacherName!="0"){return userService.findteaforadmin(teacherName);}
        return null;
    }

    @RequestMapping(value = "/getteaforadmin")
    public List<Teacher> getteaforadmin(){
        return userService.getteaforadmin();
    }

    @RequestMapping(value = "/saveorupdatetea")
    public Map addtea(@RequestBody String strtea) {
        return userService.addtea(strtea);
    }

    @RequestMapping(value = "/deletea")
    public Map deletetea(@RequestParam("teacherId") long teacherId){
        return userService.deltea(teacherId);
    }





}

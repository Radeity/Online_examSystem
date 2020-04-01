package dhu.cst.ExamSystem.controller;

import dhu.cst.ExamSystem.common.StuInfoResult;
import dhu.cst.ExamSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tea")
@PreAuthorize("hasAnyRole('ROLE_TEA')")
public class getstuforteaController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/getstufortea")
    public StuInfoResult getstufortea(@RequestParam("StudentId") long studentid){
        return userService.getstufortea(studentid);
    }

}

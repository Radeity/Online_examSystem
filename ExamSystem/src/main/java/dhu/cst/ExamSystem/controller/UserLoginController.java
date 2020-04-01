package dhu.cst.ExamSystem.controller;

import dhu.cst.ExamSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserLoginController {

    @Autowired
    private UserService userService;

    /*@PostMapping(value = "/login")
    public Map login(@RequestBody Map params) {
        String username = (String) params.get("username");
        String password = (String) params.get("password");
        return userService.login(username, password);
    }*/



}

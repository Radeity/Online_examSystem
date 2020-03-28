package dhu.cst.ExamSystem.controller;

import dhu.cst.ExamSystem.service.ChangePassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ChangePassController {
    @Autowired
    ChangePassService changePassService;

    @PostMapping(value = "/stuchangePass")
    public boolean stuchangePass(@RequestBody Map params) {
        String username = (String) params.get("username");
        String oldpassword = (String) params.get("oldpassword");
        String newpassword =  (String) params.get("newpassword");
        return changePassService.stuchangePass(username,oldpassword,newpassword);
    }

    @PostMapping(value = "/teachangePass")
    public boolean teachangePass(@RequestBody Map params) {
        String username = (String) params.get("username");
        String oldpassword = (String) params.get("oldpassword");
        String newpassword =  (String) params.get("newpassword");
        return changePassService.teachangePass(username,oldpassword,newpassword);
    }

}

package dhu.cst.ExamSystem.controller;


import dhu.cst.ExamSystem.service.IDiscussService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(tags = "学生讨论接口")
@RestController
@RequestMapping("/auth")
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public class AdminDiscussController {
    @Autowired
    IDiscussService discussService;

    @ApiOperation("管理员获取讨论列表")
    @GetMapping(value="/admingetdiscuss")
    public Map admingetdiscuss(@RequestParam("pageNum") Integer page,
                               @RequestParam("pageSize") Integer size){
        return discussService.admingetdiscuss(page,size);
    }

    @ApiOperation("管理员获取讨论数")
    @GetMapping(value="/admingetdiscussnum")
    public Map getdicussnum(){
        return discussService.admingetdiscussnum();
    }

    @ApiOperation("删除讨论")
    @DeleteMapping(value = "/deldiscuss")
    public boolean deldiscuss(@RequestParam("discussId") long discussId){
        return discussService.deldiscuss(discussId);
    }
}

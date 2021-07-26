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
@RequestMapping("/api")
@PreAuthorize("hasAnyRole('ROLE_STU')")
public class DiscussController {
    @Autowired
    IDiscussService discussService;

    @ApiOperation("添加讨论")
    @PostMapping(value="/savediscuss")
    public Map adddiscuss(@RequestBody String str) {
        return discussService.adddiscuss(str);
    }

    @ApiOperation("根据讨论ID查讨论内容")
    @GetMapping(value="/getdiscuss")
    public Map getdiscuss(@RequestParam("questionId") long questionId){
        return discussService.getdiscuss(questionId);
    }

    @ApiOperation("根据时间排序")
    @GetMapping(value="/getdiscussbytime")
    public Map getdiscussbytime(@RequestParam("questionId") long questionId,
                                @RequestParam("pageNum") Integer page){
        return discussService.getdiscussbytime(questionId,page);
    }

    @ApiOperation("根据讨论查找回复")
    @GetMapping(value="/getreplybydiscuss")
    public Map getreplybydiscuss(@RequestParam("discussId") long discussId) {
        return discussService.getreplybydiscuss(discussId);
    }

    @ApiOperation("根据点赞排序")
    @GetMapping(value="/getdiscussbythumb")
    public Map getdiscussbythumb(@RequestParam("questionId") long questionId,
                                 @RequestParam("pageNum") Integer page){
        return discussService.getdiscussbythumb(questionId,page);
    }

    @ApiOperation("点赞")
    @PostMapping(value="/thumbup")
    public boolean thumbup(@RequestParam("discussId") long discussId){
        return discussService.thumbup(discussId);
    }

    @ApiOperation("撤销点赞")
    @PostMapping(value="/unthumb")
    public boolean unthumb(@RequestParam("discussId") long discussId) {
        return discussService.unthumb(discussId);
    }


}

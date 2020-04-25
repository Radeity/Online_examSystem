package dhu.cst.ExamSystem.controller;

import dhu.cst.ExamSystem.entity.Paper;
import dhu.cst.ExamSystem.service.IPaperService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
=======
import org.springframework.web.bind.annotation.*;
>>>>>>> dev

import java.util.List;
import java.util.Map;

@Api(tags = "老师与试卷相关接口")
@RestController
@RequestMapping("/tea")
@PreAuthorize("hasAnyRole('ROLE_TEA')")
public class PaperController {
    @Autowired
    IPaperService paperService;

    @ApiOperation("插入或更新试卷")
    @PostMapping(value = "/saveorupdatepaper")
    public Map addpaper(@RequestBody String strpaper) {
        return paperService.addpaper(strpaper);
    }

    @ApiOperation("删除试卷")
    @DeleteMapping(value = "/delpaper")
    public Map deletepoint(@RequestParam("paperId") long paperId){
        return paperService.delpaper(paperId);
    }

    @ApiOperation("获取已有试卷")
    @GetMapping(value = "/getpaper")
    public List<Paper> getpaper(@RequestParam(value = "subjectId",required = false)long subjectid ) {
        return paperService.getpaper(subjectid);
    }

    @ApiOperation("根据试卷名查找试卷")
    @GetMapping(value = "/teafindpaper")
    public List<Paper> findpaper(@RequestParam("paperName") String paperName){
        return paperService.teafindPaper(paperName);
    }
}

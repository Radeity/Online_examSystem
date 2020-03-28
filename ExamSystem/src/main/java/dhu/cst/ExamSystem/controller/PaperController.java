package dhu.cst.ExamSystem.controller;

import dhu.cst.ExamSystem.entity.Paper;
import dhu.cst.ExamSystem.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PaperController {
    @Autowired
    PaperService paperService;

    @RequestMapping(value = "/saveorupdatepaper")
    public Map addpaper(@RequestBody String strpaper) {
        return paperService.addpaper(strpaper);
    }

    @RequestMapping(value = "/delpaper")
    public Map deletepoint(@RequestParam("paperId") long paperId){
        return paperService.delpaper(paperId);
    }

    @RequestMapping(value = "/getpaper")
    public List<Paper> getpaper(@RequestParam("TeacherId") long teacherId) {
        return paperService.getpaper(teacherId);
    }

    @RequestMapping(value = "/teafindpaper")
    public List<Paper> findpaper(@RequestParam("paperName") String paperName, @RequestParam("TeacherId")long TeacherId){
        return paperService.teafindPaper(paperName,TeacherId);
    }
}

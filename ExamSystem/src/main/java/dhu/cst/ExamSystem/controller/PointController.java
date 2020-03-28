package dhu.cst.ExamSystem.controller;

import dhu.cst.ExamSystem.entity.Point;
import dhu.cst.ExamSystem.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PointController {
    @Autowired
    PointService pointService;

    @RequestMapping(value = "/getpointbyteacherid")
    public List<Point> getpointbytea(@RequestParam("TeacherId") long teacherId) {
        return pointService.getpointbytea(teacherId);
    }

    @RequestMapping(value = "/getpointbysubjectid")
    public List<Point> getpointbysub(@RequestParam("subjectId") long subjectId) {
        return pointService.getpointbysub(subjectId);
    }

    @RequestMapping(value = "/saveorupdatepoint")
    public Map addpoint(@RequestBody String strpoint) {
        return pointService.addpoint(strpoint);
    }

    @RequestMapping(value = "/delepoint")
    public Map deletepoint(@RequestParam("pointId") long pointId){
        return pointService.delpoint(pointId);
    }

}

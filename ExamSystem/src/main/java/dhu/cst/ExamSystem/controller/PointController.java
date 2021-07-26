package dhu.cst.ExamSystem.controller;

import dhu.cst.ExamSystem.entity.Point;
import dhu.cst.ExamSystem.service.IPointService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "老师与知识点相关接口")
@RestController
@RequestMapping("/tea")
@PreAuthorize("hasAnyRole('ROLE_TEA')")
public class PointController {
    @Autowired
    IPointService pointService;

    @ApiOperation("根据教师查找知识点")
    @GetMapping(value = "/getpointbyteacheid")
    public List<Point> getpointbytea() {
        return pointService.getpointbytea();
    }

    @ApiOperation("根据学科查找知识点")
    @GetMapping(value = "/getpointbysubjectid")
    public List<Point> getpointbysub(@RequestParam("subjectId") long subjectId) {
        return pointService.getpointbysub(subjectId);
    }

    @ApiOperation("增加或更新知识点")
    @PostMapping(value = "/saveorupdatepoint")
    public boolean addpoint(@RequestBody Point point) {
        return pointService.addpoint(point);
    }

    @ApiOperation("删除知识点")
    @DeleteMapping(value = "/delepoint")
    public Map deletepoint(@RequestParam("pointId") long pointId){
        return pointService.delpoint(pointId);
    }

}

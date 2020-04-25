package dhu.cst.ExamSystem.service;

import dhu.cst.ExamSystem.entity.Point;

import java.util.List;
import java.util.Map;

public interface IPointService {
    List<Point> getpointbytea();
    List<Point> getpointbysub(long subjectId);
    Map addpoint(String str);
    Map delpoint(long pointid);
}

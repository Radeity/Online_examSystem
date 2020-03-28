package dhu.cst.ExamSystem.service;

import com.alibaba.fastjson.JSON;
import dhu.cst.ExamSystem.dao.PointRepository;
import dhu.cst.ExamSystem.dao.TCRepository;
import dhu.cst.ExamSystem.entity.Point;
import dhu.cst.ExamSystem.entity.Tc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PointService {
    @Autowired
    TCRepository tcRepository;
    @Autowired
    PointRepository pointRepository;

    public List<Point> getpointbytea(long teacherId){
        List<Tc> tc = tcRepository.findByTeacherId(teacherId);
        List<Point> point = new ArrayList<Point>();
      //  long subjectId=0;
        for(int i=0;i<tc.size();i++){
            point.addAll(pointRepository.findBySubjectId(tc.get(i).getSubjectId()));
        }
        return point;
    }

    public List<Point> getpointbysub(long subjectId) {
        return pointRepository.findBySubjectId(subjectId);
    }


    public Map addpoint(String str){
        Map m = new HashMap();
        List<Point> lpoint = JSON.parseArray(JSON.parseObject(str).getString("point"), Point.class);
        if(lpoint.size()!=0){
            Point point = lpoint.get(0);
            pointRepository.save(point);
            m.put("msg","Operating successfully!");
        }
        return m;
    }

    public Map delpoint(long pointid){
        Map m = new HashMap();
        pointRepository.deleteByPointId(pointid);
        m.put("msg","Delete successfully!");
        return m;
    }
}

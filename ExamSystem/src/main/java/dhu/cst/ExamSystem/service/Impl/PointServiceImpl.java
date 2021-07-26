package dhu.cst.ExamSystem.service.Impl;

import dhu.cst.ExamSystem.dao.ClassInfoRepository;
import dhu.cst.ExamSystem.dao.PointRepository;
import dhu.cst.ExamSystem.entity.Classinfo;
import dhu.cst.ExamSystem.entity.Point;
import dhu.cst.ExamSystem.service.IPointService;
import dhu.cst.security.entity.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("PointService")
@Transactional
public class PointServiceImpl implements IPointService {
    @Autowired
    ClassInfoRepository classInfoRepository;
    @Autowired
    PointRepository pointRepository;
    @Autowired
    CurrentUser currentUser;

    @Override
    public List<Point> getpointbytea(){
        long teacherId = currentUser.getCurrentUser().getId();
        List<Classinfo> classinfos = classInfoRepository.findByTeacherId(teacherId);
        List<Point> point = new ArrayList<Point>();
        for(int i=0;i<classinfos.size();i++){
            point.addAll(pointRepository.findBySubjectId(classinfos.get(i).getSubjectId()));
        }
        Set set = new HashSet();
        List newList = new ArrayList();
        set.addAll(point);
        newList.addAll(set);
        return newList;
    }

    @Override
    public List<Point> getpointbysub(long subjectId) {
        return pointRepository.findBySubjectId(subjectId);
    }

    @Override
    public List<Point> findbypointname(String pointname){
        return pointRepository.findByPointName(pointname);
    };

    @Override
    public boolean addpoint(Point point){
        pointRepository.save(point);
        return true;
    }

    @Override
    public Map delpoint(long pointid){
        Map m = new HashMap();
        pointRepository.deleteByPointId(pointid);
        m.put("msg","Delete successfully!");
        return m;
    }

}

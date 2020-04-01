package dhu.cst.ExamSystem.service;

import dhu.cst.ExamSystem.dao.ScoreRepository;
import dhu.cst.ExamSystem.dao.TCRepository;
import dhu.cst.ExamSystem.entity.Classinfo;
import dhu.cst.ExamSystem.entity.Score;
import dhu.cst.ExamSystem.entity.Subject;
import dhu.cst.ExamSystem.entity.Tc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class GradeService {
    @Autowired
    TCRepository tcRepository;
    @Autowired
    ScoreRepository scoreRepository;

    public List<Classinfo> classQuery(long teacherid) {
        List<Tc> tc = tcRepository.findByTeacherId(teacherid);
        List<Classinfo> classinfos = new ArrayList<Classinfo>();
        Set set = new HashSet();
        for(int i=0;i<tc.size();i++){
            long subjectid = tc.get(i).getSubjectId();
            List<Classinfo> newcis = tcRepository.findClassId(subjectid);
            set.addAll(newcis);
        }
        classinfos.addAll(set);
        return classinfos;
    }

    public List<Subject> subjectQuery(long teacherid) {
        return tcRepository.findSubjectName(teacherid);
    }

    public List<Score> findgradebyname(String studentname) {
        return scoreRepository.findAllByStudentNameContaining(studentname);
    }

    public List<Score> findgradebysubject(long subjectid) {
        return scoreRepository.findBySubjectId(subjectid);
    }

    public List<Score> findgradebyclass(long classid) {
        return scoreRepository.findByClassId(classid);
    }

}


package dhu.cst.ExamSystem.service.Impl;

import dhu.cst.ExamSystem.dao.ScoreviewRepository;
import dhu.cst.ExamSystem.entity.Scoreview;
import dhu.cst.ExamSystem.entity.Subject;
import dhu.cst.ExamSystem.service.IGradeService;
import dhu.cst.ExamSystem.service.ITeaService;
import dhu.cst.security.entity.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("GradeService")
@Transactional
public class GradeServiceImpl implements IGradeService {

    @Autowired
    ScoreviewRepository scoreRepository;
    @Autowired
    CurrentUser currentUser;
    @Autowired
    ITeaService teaService;

    @Override
    public List<Scoreview> integratedquery(String subjectName,String examName,String teacherName,Integer page,Integer size){
        Pageable pageable = PageRequest.of(page-1,size);
        Page<Scoreview> pc = scoreRepository.integratedquery(subjectName, examName, teacherName, pageable);
        return pc.getContent();
    }
    @Override
    public List<Scoreview> findgradebysubject(long subjectid,Integer page,Integer size) {
        Pageable pageable = PageRequest.of(page-1,size);
        Page<Scoreview> pc = scoreRepository.findBySubjectId(subjectid,pageable);
        return pc.getContent();
    }
    @Override
    public List<Scoreview> findgradebyname(String studentname) {
        return scoreRepository.findAllByStudentNameContaining(studentname);
    }
    @Override
    public List<Scoreview> findgradebyteacher(Integer page,Integer size) {
        List<Subject> subjects = teaService.findsubject();
        List<Scoreview> scores = new ArrayList<>();
        for(Subject subject : subjects){
            Pageable pageable = PageRequest.of(page-1,size);
            Page<Scoreview> pc = scoreRepository.findBySubjectId(subject.getSubjectId(),pageable);
            scores.addAll(pc.getContent());
        }
        return scores;
    }
    @Override
    public List<Scoreview> findgradebyclass(long classid,Integer page,Integer size) {
        Pageable pageable = PageRequest.of(page-1,size);
        Page<Scoreview> pc = scoreRepository.findByClassId(classid,pageable);
        return pc.getContent();
    }
    @Override
    public List<Scoreview> findgradebyclassid(long classid,long examid) {
        return scoreRepository.findByClassIdAndExamId(classid,examid);
    }
    @Override
    public List<Scoreview> stufindgrade(){
        long studentId = currentUser.getCurrentUser().getId();
        return scoreRepository.findByStudentId(studentId);
    }
}


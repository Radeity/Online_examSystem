package dhu.cst.ExamSystem.service.Impl;

import dhu.cst.ExamSystem.dao.ClassInfoRepository;
import dhu.cst.ExamSystem.dao.ExamRepository;
import dhu.cst.ExamSystem.dao.TeacherviewRepository;
import dhu.cst.ExamSystem.entity.Exam;
import dhu.cst.ExamSystem.entity.Subject;
import dhu.cst.ExamSystem.entity.Teacherview;
import dhu.cst.ExamSystem.service.ITeaService;
import dhu.cst.security.entity.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("TeaService")
@Transactional
public class TeaServiceImpl implements ITeaService {
    @Autowired
    private ClassInfoRepository classInfoRepository;
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private CurrentUser currentUser;
    @Autowired
    private TeacherviewRepository teacherviewRepository;

    @Override
    public List<Subject> findsubject(){
        long teacherid = currentUser.getCurrentUser().getId();
        return classInfoRepository.findSubjectName(teacherid);
    }

    @Override
    public Map getExamdetails(long examid){
        Map m = new HashMap();
        List<Exam> exam = examRepository.findByExamId(examid);
        m.put("exam",exam);
        return m;
    }

    @Override
    public List<Teacherview> teacherQuery(long subjectId){
       return teacherviewRepository.findBySubjectId(subjectId);
    }
    @Override
    public List<Exam> getExamlist(long subjectid){
        return examRepository.findBySubjectId(subjectid);
    }
    @Override
    public List<Exam> getpublishExam(){
        long teacherid = currentUser.getCurrentUser().getId();
        return examRepository.queryExamList(teacherid);
    }

   /* public List<Classinfo> getclass(){
        long teacherId = currentUser.getCurrentUser().getId();
        List<Classinfo> tc = classInfoRepository.findByTeacherId(teacherId);
        List<Classinfo> classinfos = new ArrayList<Classinfo>();
        Set set = new HashSet();
        for(int i=0;i<tc.size();i++){
            long subjectid = tc.get(i).getSubjectId();
            List<Classinfo> newcis = tcRepository.findClassId(subjectid);
            set.addAll(newcis);
        }
        classinfos.addAll(set);
        return classinfos;
    }*/

}

package dhu.cst.ExamSystem.service.Impl;

import com.alibaba.fastjson.JSON;
import dhu.cst.ExamSystem.dao.*;
import dhu.cst.ExamSystem.entity.*;
import dhu.cst.ExamSystem.service.IExamsetService;
import dhu.cst.security.entity.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("ExamsetService")
@Transactional
public class ExamsetServiceImpl implements IExamsetService {

    @Autowired
    ClassInfoRepository classRepository;
    @Autowired
    ExamRepository examRepository;
    @Autowired
    PaperRepository paperRepository;
    @Autowired
    ECRepository ecRepository;
    @Autowired
    CurrentUser currentUser;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ScoreRepository scoreRepository;
    @Autowired
    AnswerRepository answerRepository;

    @Override
    public List<Classinfo> classQuery(long subjectid){
        return classRepository.findBySubjectId(subjectid);

    }
    @Override
    public List<Classinfo> classQuerybyteacher(long subjectId,long teacherId){
        return classRepository.findBySubjectIdAndTeacherId(subjectId,teacherId);
    }
    @Override
    public Map regExam(String examinfo){
        Map m = new HashMap();
        Exam exam = new Exam();
        Paper paper = new Paper();
        List<Exam> lexam = JSON.parseArray(JSON.parseObject(examinfo).getString("exam"), Exam.class);
        Set classinfos = new HashSet();

        exam = lexam.get(0);
        List<Classinfo> classes = classQuery(exam.getSubjectId());
        for(int i=0;i<classes.size();i++){
            classinfos.add(classes.get(i));
        }
        exam.setClassset(classinfos);
        examRepository.save(exam);
        if(exam.getState().equals("published")){
            publish(exam.getExamId());
        }
        /*if(lexam.size()!=0){
                examRepository.save(exam);
        }
        for(int i=0;i<classes.size();i++){
            List<Classinfo> lclassinfo = classRepository.findByClassName(classes.get(i));
            if(lclassinfo.size()!=0){
                Ec ec = new Ec();
                Classinfo classinfo = lclassinfo.get(0);
                ec.setClassId(classinfo.getClassId());
                ec.setExamId(exam.getExamId());
                ecRepository.save(ec);
            }
        }*/
        m.put("msg","Operating successfully!");
        return m;
    }
    @Override
    public Map delexam(long examid){
        Map m = new HashMap();
        examRepository.deleteByExamId(examid);
        m.put("msg","Delete successfully!");
        return m;
    }
    @Override
    public List<Exam> findExam(String ExamName){
        long classid=0,examid=0;
        List<Long> longList = new ArrayList();
        List<Exam> lexam = examRepository.findAllByExamNameContaining(ExamName);
        List<Exam> examrs = new ArrayList<>();
        long teacherId = currentUser.getCurrentUser().getId();
        List<Classinfo> lclass = classRepository.findByTeacherId(teacherId);

        for(int i=0;i<lclass.size();i++){
            classid = lclass.get(i).getClassId();
            List<Exam> lee = examRepository.findByClassId(classid);
            if(lee.size()!=0){
                examid = lee.get(0).getExamId();
            }
            longList.add(examid);
        }
        for(int i=0;i<lexam.size();i++){
            if(longList.contains(lexam.get(i).getExamId())){
                examrs.add(lexam.get(i));
            }
        }
        return examrs;
    }
    @Override
    public boolean publish(long examid){
        List<Exam> examl = examRepository.findByExamId(examid);
        if(examl.size()!=0){
            Exam exam = examl.get(0);
            exam.setState("published");
            examRepository.save(exam);
            List<Ec> ec = ecRepository.findByExamId(examid);
            for(int i=0;i<ec.size();i++){
                long classId = ec.get(i).getClassId();
                Classinfo c = classRepository.findByClassId(classId);
                String className = c.getClassName();
                long teacherId = c.getTeacherId();
                String teacherName = userRepository.findById(teacherId).getName();
                List<User> users = userRepository.findByClassId(classId);
                for(int j=0;j<users.size();j++){
                    User user = users.get(j);
                    Score score = new Score();
                    score.setScore(-1);
                    score.setClassId(classId);
                    score.setClassName(className);
                    score.setPaperId(exam.getPaperId());
                    score.setStudentId(user.getId());
                    score.setStudentName(user.getName());
                    score.setExamId(examid);
                    score.setExamName(exam.getExamName());
                    score.setTeacherName(teacherName);
                    score.setSubjectId(exam.getSubjectId());
                    score.setSubjectName(exam.getSubjectName());

                    scoreRepository.save(score);
                }


            }
            return true;
        }
        return false;
    }
    @Override
    public boolean unpublish(long examid){
        List<Exam> exams = examRepository.findByExamId(examid);
        if(exams.size()!=0){
            Exam exam = exams.get(0);
            long examId = exam.getExamId();
            exam.setState("unpublished");
            examRepository.save(exam);
            long paperId = exam.getPaperId();
            answerRepository.deleteByPaperId(paperId);
            scoreRepository.deleteByExamId(examId);
            return true;
        }
        return false;
    }

}

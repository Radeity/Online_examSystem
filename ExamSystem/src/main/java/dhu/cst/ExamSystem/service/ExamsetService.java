package dhu.cst.ExamSystem.service;

import com.alibaba.fastjson.JSON;
import dhu.cst.ExamSystem.dao.*;
import dhu.cst.ExamSystem.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExamsetService {
    @Autowired
    TCRepository tcRepository;
    @Autowired
    ClassInfoRepository classRepository;
    @Autowired
    ExamRepository examRepository;
    @Autowired
    PaperRepository paperRepository;
    @Autowired
    ECRepository ecRepository;

    public Map classQuery(long teacherId){
        Map m = new HashMap();
        List<Tc> tc = tcRepository.findByTeacherId(teacherId);
        List<Classinfo> classinfos = new ArrayList<Classinfo>();
        Set set = new HashSet();
        for(int i=0;i<tc.size();i++){
            long subjectid = tc.get(i).getSubjectId();
            List<Classinfo> newcis = tcRepository.findClassId(subjectid);
            set.addAll(newcis);
        }
        classinfos.addAll(set);
        if(classinfos.size()!=0) {
            m.put("class",classinfos);
            System.out.println("1");
            return m;
        }
        else{
            m.put("class","none");
            return m;
        }
    }

    public Map regExam(String examinfo){
        Map m = new HashMap();
    //    long paperId=0;
        Exam exam = new Exam();
        Paper paper = new Paper();
       // String pn = JSONObject.parseObject(examinfo).getString("PaperName");
        List<String> classes = JSON.parseArray(JSON.parseObject(examinfo).getString("classes"), String.class);
        //System.out.println(classes.get(0));
        List<Exam> lexam = JSON.parseArray(JSON.parseObject(examinfo).getString("exam"), Exam.class);
        if(lexam.size()!=0){
            exam = lexam.get(0);
      //      paper.setPaperId(exam.getPaperId());
        //    paper.setSubjectId(exam.getSubjectId());
     //       paper.setPaperName(pn);
       //     paperRepository.save(paper);
      //      List<Paper> allpaper = paperRepository.findAll();
       //     paperId = allpaper.get(allpaper.size()-1).getPaperId();
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
        }
        m.put("msg","Operating successfully!");
        return m;
    }

    public Map delexam(long examid){
        Map m = new HashMap();
        examRepository.deleteByExamId(examid);
        m.put("msg","Delete successfully!");
        return m;
    }
    public List<Exam> findExam(String ExamName, long teacherId){
        long classid=0,examid=0;
        List<Long> longList = new ArrayList();
        List<Exam> lexam = examRepository.findAllByExamNameContaining(ExamName);
        List<Exam> examrs = new ArrayList<>();
        List<Tc> tc = tcRepository.findByTeacherId(teacherId);

        for(int i=0;i<tc.size();i++){
            classid = tc.get(i).getClassId();
            List<Exam> lee = examRepository.findByClassId(classid);
            if(lee.size()!=0){
                examid = lee.get(0).getExamId();
            }
            longList.add(examid);
            //   List<Exam> exams = examRepository.findByClassId(classid);
        }
        for(int i=0;i<lexam.size();i++){
            if(longList.contains(lexam.get(i).getExamId())){
                examrs.add(lexam.get(i));
            }
        }
        //List<Exam> exams = examRepository.findByClassId(classid);
        return examrs;
    }

}

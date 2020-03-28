package dhu.cst.ExamSystem.service;

import com.alibaba.fastjson.JSON;
import dhu.cst.ExamSystem.dao.ExamRepository;
import dhu.cst.ExamSystem.dao.PdetailRepository;
import dhu.cst.ExamSystem.dao.QuestionRepository;
import dhu.cst.ExamSystem.dao.TCRepository;
import dhu.cst.ExamSystem.entity.Exam;
import dhu.cst.ExamSystem.entity.Pdetail;
import dhu.cst.ExamSystem.entity.Question;
import dhu.cst.ExamSystem.entity.Tc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    TCRepository tcRepository;
    @Autowired
    PdetailRepository pdetailRepository;
    @Autowired
    ExamRepository examRepository;

    public List<Question> getquestion(long teacherId){
        List<Tc> tc = tcRepository.findByTeacherId(teacherId);
        long subjectId=0;
        if(tc.size()!=0){
            subjectId = tc.get(0).getSubjectId();
        }
        return questionRepository.findBySubjectId(subjectId);
    }

    public Map addquestion(String queinfo){
        Map m = new HashMap();
        List<Question> lquestion = JSON.parseArray(JSON.parseObject(queinfo).getString("question"), Question.class);
        if(lquestion.size()!=0){
            Question question = lquestion.get(0);
            questionRepository.save(question);
            m.put("msg","Operating successfully!");
        }
        return m;
    }

    public Map delquestion(long questionid){
        Map m = new HashMap();
        questionRepository.deleteByQuestionId(questionid);
        m.put("msg","Delete successfully!");
        return m;
    }

    public Map addquestiontoexam(long questionId, long paperId){
        Map m = new HashMap();

        List<Question> que = questionRepository.findByQuestionId(questionId);
        if(que.size()!=0){
            Question question = que.get(0);
            Pdetail pd = new Pdetail();
            pd.setPaperId(paperId);
            pd.setQuestionId(questionId);
            pd.setQuestionType(question.getQuestionType());
            pd.setQuestionName(question.getQuestionName());
            pd.setOpt(question.getOpt());
            pd.setCorAnswer(question.getQuestionAnswer());
            pdetailRepository.save(pd);
            m.put("msg","Add successfully!");
        }
        return m;
    }

    public Map delquefromexam(long questionid, long paperid){
        Map m = new HashMap();
        pdetailRepository.deleteByQuestionIdAndPaperId(questionid,paperid);
        m.put("msg","Delete successfully!");
        return m;
    }

    public Map getquestionnum(long paperId){
        Map m = new HashMap();
        List<Exam> lexam = examRepository.findByPaperId(paperId);
        long snum=0,mnum=0,bnum=0,snow=0,mnow=0,bnow=0;
        if(lexam.size()!=0){
            Exam exam = lexam.get(0);
            snum = exam.getSingleNum();
            mnum = exam.getMultiNum();
            bnum = exam.getBlankNum();
        }
        long stype=0,mtype=1,btype=2;
        snow = pdetailRepository.countByPaperIdAndAndQuestionType(paperId,stype);
        mnow = pdetailRepository.countByPaperIdAndAndQuestionType(paperId,mtype);
        bnow = pdetailRepository.countByPaperIdAndAndQuestionType(paperId,btype);
        m.put("single_added",snow);
        m.put("multiple_added",mnow);
        m.put("blank_added",bnow);
        m.put("single_total",snum);
        m.put("multiple_total",mnum);
        m.put("blank_total",bnum);

        return m;
    }

}

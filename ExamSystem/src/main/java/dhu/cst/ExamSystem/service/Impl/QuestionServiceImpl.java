package dhu.cst.ExamSystem.service.Impl;

import com.alibaba.fastjson.JSON;
import dhu.cst.ExamSystem.common.AccuracyResult;
import dhu.cst.ExamSystem.common.RateResult;
import dhu.cst.ExamSystem.dao.*;
import dhu.cst.ExamSystem.entity.*;
import dhu.cst.ExamSystem.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("QuestionService")
@Transactional
public class QuestionServiceImpl implements IQuestionService {
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    PdetailRepository pdetailRepository;
    @Autowired
    ExamRepository examRepository;
    @Autowired
    PaperRepository paperRepository;
    @Autowired
    PointRepository pointRepository;
    @Autowired
    AnswerRepository answerRepository;

    @Override
    public List<String> getbank(long subjectId){
        return questionRepository.getbanks(subjectId);
    }

    @Override
    public List<Question> getquestion(long subjectId,Integer page,Integer size){
        Pageable pageable = PageRequest.of(page-1,size);
        Page<Question> qp =  questionRepository.findBySubjectIdAndPointIdIsNotNull(subjectId,pageable);
        return qp.getContent();
    }

    @Override
    public Map integratedquery(String bankName,long pointId,long type,Integer page,Integer size){
        Pageable pageable = PageRequest.of(page-1,size);
        System.out.println(type);
        Page<Question> pq = questionRepository.integratedquery(bankName,pointId,type,pageable);
        Map m = new HashMap();
        Integer qnum = questionRepository.integratedquery(bankName,pointId,type);
        m.put("questions",pq.getContent());
        m.put("quesnum",qnum);
        return m;
    }

    @Override
    public Map getquefromexam(long paperId,Integer page,Integer size){
        Pageable pageable = PageRequest.of(page-1,size);
        Page<Pdetail> pd = pdetailRepository.findByPaperIdOrderByQuestionType(paperId,pageable);
        Map m = new HashMap();
        int qnum = pdetailRepository.countByPaperId(paperId);
        m.put("questions",pd.getContent());
        m.put("quesnum",qnum);
        return m;
    }

    @Override
    public List<Question> getquestionbybank(String bankName,Integer page,Integer size){
        Pageable pageable = PageRequest.of(page-1,size);
        Page<Question> qp =  questionRepository.findByBankNameAndPointIdIsNotNull(bankName,pageable);
        return qp.getContent();
    }

    @Override
    public List<Question> getquestionbypoint(long subjectId,long pointId,Integer page,Integer size){
        Pageable pageable = PageRequest.of(page-1,size);
        Page<Question> qp = questionRepository.findBySubjectIdAndPointId(subjectId,pointId,pageable);
        return qp.getContent();
    }

    @Override
    public List<Question> getquestionbytype(long subjectId,long type,Integer page,Integer size){
        Pageable pageable = PageRequest.of(page-1,size);
        Page<Question> qp = questionRepository.findBySubjectIdAndQuestionType(subjectId,type,pageable);
        return qp.getContent();
    }

    @Override
    public List<Pdetail> getquefromexambypoint(long paperId,long pointId,Integer page,Integer size){
        Pageable pageable = PageRequest.of(page-1,size);
        Page<Pdetail> pd = pdetailRepository.findByPaperIdAndPointIdOrderByQuestionType(paperId,pointId,pageable);
        return pd.getContent();
    }

    @Override
    public List<Pdetail> getquefromexambytype(long paperId,long type,Integer page,Integer size){
        Pageable pageable = PageRequest.of(page-1,size);
        Page<Pdetail> pd = pdetailRepository.findByPaperIdAndQuestionTypeOrderByQuestionType(paperId,type,pageable);
        return pd.getContent();
    }

    @Override
    public Map addquestion(String queinfo){
        Map m = new HashMap();
        List<Question> lquestion = JSON.parseArray(JSON.parseObject(queinfo).getString("question"), Question.class);
        if(lquestion.size()!=0){
            Question question = lquestion.get(0);
            String opt = question.getOpt();

            questionRepository.save(question);
            m.put("msg","Operating successfully!");
        }
        return m;
    }

    @Override
    public boolean saveall(List<Question> lq){
        questionRepository.saveAll(lq);
        return true;
    }


    @Override
    public Map delquestion(long questionid){
        Map m = new HashMap();
        questionRepository.deleteByQuestionId(questionid);
        m.put("msg","Delete successfully!");
        return m;
    }

    @Override
    public boolean addbank(long subjectId,String bankname){
        questionRepository.savebank(subjectId,bankname);
        return true;
    }

    @Override
    public boolean changebankname(String oldname,String newname){
        questionRepository.updatebankname(oldname,newname);
        return true;
    }

    @Override
    public boolean delbank(String bankname){
        questionRepository.deleteByBankName(bankname);
        return true;
    }

    @Override
    public Map addquestiontoexam(long questionId, long paperId){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Map m = new HashMap();
        List<Pdetail> pdl = pdetailRepository.findByPaperIdAndQuestionId(paperId,questionId);
        if(pdl.size()!=0){
            m.put("msg","请勿重复添加");
            return m;
        }
        List<Question> que = questionRepository.findByQuestionIdAndPointIdIsNotNull(questionId);
        Map n = getquestionnum(paperId);
        switch((int) que.get(0).getQuestionType()){
            case 0:
                if(n.get("single_added")==n.get("single_total")){
                    m.put("msg","单选题数量已超出范围，添加失败！");
                    return m;
                }
                break;
            case 1:
                if(n.get("multiple_added")==n.get("multiple_total")){
                    m.put("msg","多选题数量已超出范围，添加失败！");
                    return m;
                }
                break;
            case 2:
                if(n.get("blank_added")==n.get("blank_total")){
                    m.put("msg","填空题数量已超出范围，添加失败！");
                    return m;
                }
                break;
        }
        if(que.size()!=0){
            Question question = que.get(0);
            Pdetail pd = new Pdetail();
            pd.setPaperId(paperId);
            pd.setQuestionId(questionId);
            pd.setQuestionType(question.getQuestionType());
            pd.setQuestionName(question.getQuestionName());
            pd.setOpt(question.getOpt());
            pd.setCorAnswer(question.getQuestionAnswer());
            pd.setPointId(question.getPointId());
            pdetailRepository.save(pd);
            n = getquestionnum(paperId);
            System.out.println(n.get("single_added"));
            switch((int) pd.getQuestionType()){
                case 0:
                    m.put("msg","添加成功! 单选题："+n.get("single_added")+"/"+n.get("single_total"));
                    break;
                case 1:
                    m.put("msg","添加成功! 多选题："+n.get("multiple_added")+"/"+n.get("multiple_total"));
                    break;
                case 2:
                    m.put("msg","添加成功! 填空题："+n.get("blank_added")+"/"+n.get("blank_total"));
                    break;
            }
        }
        return m;
    }

    @Override
    public Map delquefromexam(long questionid, long paperid){
        Map m = new HashMap();
        pdetailRepository.deleteByQuestionIdAndPaperId(questionid,paperid);
        m.put("msg","Delete successfully!");
        return m;
    }

    @Override
    public Map getquestionnum(long paperId){
        Map m = new HashMap();
        List<Paper> lpaper =paperRepository.findByPaperId(paperId);
        long snum=0,mnum=0,bnum=0,snow=0,mnow=0,bnow=0;
        if(lpaper.size()!=0){
            Paper paper = lpaper.get(0);
            snum = paper.getSingleNum();
            mnum = paper.getMultiNum();
            bnum = paper.getBlankNum();
        }
        long stype=0,mtype=1,btype=2;
        snow = pdetailRepository.countByPaperIdAndQuestionType(paperId,stype);
        mnow = pdetailRepository.countByPaperIdAndQuestionType(paperId,mtype);
        bnow = pdetailRepository.countByPaperIdAndQuestionType(paperId,btype);
        m.put("single_added",snow);
        m.put("multiple_added",mnow);
        m.put("blank_added",bnow);
        m.put("single_total",snum);
        m.put("multiple_total",mnum);
        m.put("blank_total",bnum);

        return m;
    }

    @Override
    public Map analyse(long ExamId){
        Map m = new HashMap();
        String state = "published";
        Exam exam = examRepository.findByExamIdAndState(ExamId,state).get(0);
        long paperId = exam.getPaperId();
        List<Long> pids = pdetailRepository.finddistinctpoint(paperId);
        m.put("pointnum",pids.size());
        for(int i=0;i<pids.size();i++){
            int allcorrect=0,allnum=0;
            long pointId = pids.get(i);
            Point p = pointRepository.findById(pointId).get();
            m.put("point"+String.valueOf(i+1),p);
            List<Pdetail> pds = pdetailRepository.findByPaperIdAndPointIdOrderByQuestionType(paperId,pointId);
            m.put("questions"+String.valueOf(i+1),pds);
            List<RateResult> larc = new ArrayList<>();
            List<RateResult> larw = new ArrayList<>();
            for(int j=0;j<pds.size();j++){
                int qcorrect=0,qnum=0,qwrong=0;
                long questionId = pds.get(j).getQuestionId();
                String coranswer = pds.get(j).getCorAnswer();
                String wroanswer="";
                List<Answer> answers = answerRepository.findByQuestionId(questionId);
                qnum = answers.size();
                allnum+=qnum;
                List<AccuracyResult> ars = answerRepository.findWrongAccuracy(questionId);
                boolean cflag=false;
                boolean wflag=false;
                for(AccuracyResult ar:ars){
                    if(ar.getAnswer().equals(coranswer)){
                        allcorrect+=ar.getCount();
                        qcorrect+=ar.getCount();
                        cflag=true;
                        if(qwrong!=0) break;
                    }
                    else{
                        if(!wflag){
                            qwrong+=ar.getCount();
                            wroanswer = ar.getAnswer();
                            wflag=true;
                        }
                        if(cflag) break;
                    }
                }
                if(pds.get(j).getQuestionId()==38){
                    System.out.println(qwrong+" "+qnum);
                }
                RateResult rrc = new RateResult(coranswer,(double)qcorrect/qnum);
                RateResult rrw = new RateResult(wroanswer,(double)qwrong/qnum);

                larc.add(rrc);
                larw.add(rrw);
            }
            m.put("correct"+String.valueOf(i+1),larc);
            m.put("wrong"+String.valueOf(i+1),larw);
            m.put("pointaccuracy"+String.valueOf(i+1),(double)allcorrect/allnum);
        }
        return m;
    }

}

package dhu.cst.ExamSystem.service.Impl;

import dhu.cst.ExamSystem.dao.*;
import dhu.cst.ExamSystem.entity.*;
import dhu.cst.ExamSystem.service.IExamService;
import dhu.cst.security.entity.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

@Service("ExamService")
@Transactional
public class ExamServiceImpl implements IExamService {
    @Autowired
    private PaperRepository paperRepository;
    @Autowired
    private SCRepository scRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private ScoreRepository scoreRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PaperviewRepository paperviewRepository;
    @Autowired
    private CurrentUser currentUser;
    @Autowired
    private AnswerviewRepository answerviewRepository;
    @Autowired
    private PdetailRepository pdetailRepository;


    @Override
    public Map getPaper(long examId){
        Map m = new HashMap();
        List<Paper> p = paperRepository.findByExamId(examId);
        if(p.size()!=0) {
            long paperId = (long) p.get(0).getPaperId();
            Paper paperinfo = paperRepository.findByPaperId(paperId).get(0);
            List<Paperview> prs = paperviewRepository.findByPaperIdOrderByQuestionType(paperId);
            m.put("paperId",paperinfo);
            m.put("question",prs);
            m.put("studentId",currentUser.getCurrentUser().getId());
            return m;
        }
        else return null;
    }

    @Override
    public Map submit(Timestamp submit, List<Answer> answer){
        Map m = new HashMap();
        if(answer.size()!=0){
            long studentId = currentUser.getCurrentUser().getId();
            if(studentId != answer.get(0).getStudentId()){          //防止用学生权限恶意提交他人答案
                m.put("msg","提交失败！");
                return m;
            }
            List<Score> scores = scoreRepository.findByStudentIdAndPaperId(studentId,answer.get(0).getPaperId());
            if(scores.get(0).getScore()!=-1){
                m.put("msg","请勿重复提交");
            }
            long paperId = answer.get(0).getPaperId();
            long scorecal=0,sscore=0,snum=0,mscore=0,mnum=0,bscore=0,bnum=0,spoint=0,mpoint=0,bpoint=0;
            List<Paper> paperList = paperRepository.findByPaperId(paperId);
            if(paperList.size()!=0){
                Paper paper = paperList.get(0);
                sscore = paper.getSingleScore();
                snum = paper.getSingleNum();
                mscore = paper.getMultiScore();
                mnum = paper.getMultiNum();
                bscore = paper.getBlankScore();
                bnum = paper.getBlankNum();
                if(snum!=0) spoint = sscore / snum;
                if(mnum!=0) mpoint = mscore / mnum;
                if(bnum!=0) bpoint = bscore / bnum;
            }
            for(int i=0;i<answer.size();i++){
                long questionId = answer.get(i).getQuestionId();
                Pdetail check = pdetailRepository.findByQuestionId(questionId).get(0);
                String coranswer = check.getCorAnswer();
                answer.get(i).setCorAnswer(coranswer);
                long questionType = check.getQuestionType();
                if(answer.get(i).getStuAnswer().equals(coranswer)){
                    answer.get(i).setAnswerCheck("T");
                    System.out.println(questionType);
                    switch ((int) questionType){
                        case 0:
                            scorecal += spoint;
                            answer.get(i).setAnswerPoint(spoint);
                            break;
                        case 1:
                            scorecal += mpoint;
                            answer.get(i).setAnswerPoint(mpoint);
                            break;
                        case 2:
                            scorecal += bpoint;
                            answer.get(i).setAnswerPoint(bpoint);
                            break;
                    }
                }
                else answer.get(i).setAnswerCheck("F");
                answerRepository.save(answer.get(i));
            }

            User user =  userRepository.findById(studentId);
            List<Sc> sc = scRepository.findByStudentId(studentId);

            Score score = scoreRepository.findByStudentIdAndPaperId(studentId,paperId).get(0);
            score.setScore(scorecal);
            score.setSubmit(submit);
            scoreRepository.save(score);
            m.put("submit","success");
            m.put("score",scorecal);
            return m;
        }
        m.put("submit","failed");
        return m;
    }

    @Override
    public String examstate(long examId){
        long studentId = currentUser.getCurrentUser().getId();
        List<Paper> p = paperRepository.findByExamId(examId);
        if(p.size()!=0) {
            long paperId = (long) p.get(0).getPaperId();
            List<Score> score = scoreRepository.findByStudentIdAndPaperId(studentId,paperId);
            if(score.size()!=0){
                return String.valueOf(score.get(0).getScore());
            }
        }
        return  "unfinished";
    }
    @Override
    public Map paperdetail(long examId) {
        Map m = new HashMap();
        List<Paper> p = paperRepository.findByExamId(examId);
        if(p.size()!=0) {
            long studentId = currentUser.getCurrentUser().getId();
            long paperId = (long) p.get(0).getPaperId();
            Paper paper = paperRepository.findByExamId(examId).get(0);
            List<Score> scores = scoreRepository.findByStudentIdAndPaperId(studentId,paperId);
            List<Answerview> av = answerviewRepository.findByStudentIdAndPaperIdOrderByQuestionType(studentId,paperId);
            int t=0;
            int f=0;
            for(Answerview an:av){
                if(an.getAnswerCheck().equals("T")){
                    t++;
                }
                else{
                    f++;
                }
            }
            System.out.println(t+f);
            m.put("correct",t);
            m.put("wrong",f);
            m.put("paper",paper);
            m.put("score",scores);
            m.put("answer",av);

            return m;
        }
        return null;
    }
    @Override
    public Map paperdetail(long studentId,long examId) {
        Map m = new HashMap();
        List<Paper> p = paperRepository.findByExamId(examId);
        if(p.size()!=0) {
            long paperId = (long) p.get(0).getPaperId();
            System.out.println(paperId);
            Paper paper = paperRepository.findByExamId(examId).get(0);
            List<Score> scores = scoreRepository.findByStudentIdAndPaperId(studentId,paperId);
            List<Answerview> av = answerviewRepository.findByStudentIdAndPaperIdOrderByQuestionType(studentId,paperId);
            int t=0;
            int f=0;
            for(Answerview an:av){
                if(an.getAnswerCheck().equals("T")){
                    t++;
                }
                else{
                    f++;
                }
            }
            System.out.println(t+f);
            m.put("correct",t);
            m.put("wrong",f);
            m.put("paper",paper);
            m.put("score",scores);
            m.put("answer",av);
            return m;
        }
        return null;
    }

    @Override
    public Map getfavorite(){
        Map m = new HashMap();
        long studentId = currentUser.getCurrentUser().getId();
        List<Answerview> av = answerviewRepository.findByStudentIdAndFavorite(studentId,1);
        m.put("favorite",av);
        return m;
    }

    @Override
    public Map findExam(String ExamName){
        Map m = new HashMap();
        long classid=0,examid=0;
        long studentId = currentUser.getCurrentUser().getId();
        List<Long> longList = new ArrayList();
        List<Exam> lexam = examRepository.findAllByExamNameContaining(ExamName);
        List<Exam> examrs = new ArrayList<>();
        List<Sc> sc = scRepository.findByStudentId(studentId);
        String state = "published";
        for(int i=0;i<sc.size();i++){
            classid = sc.get(i).getClassId();
            List<Exam> lee = examRepository.findByStateClassId(classid,state);
         //   List<Exam> lee = examRepository.findByClassId(classid);               //deprecated
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
        m.put("examinfo",examrs);
        List<String> lstate = new ArrayList<String>();
        for(int i=0;i<examrs.size();i++){
            lstate.add(examstate(examrs.get(i).getExamId()));
        }
        m.put("state",lstate);
        return m;
    }
    @Override
    public Map getExam(){
        Map m = new HashMap();
        long classid=0;
        List<Exam> examrs = new ArrayList<>();
        long studentId = currentUser.getCurrentUser().getId();
        System.out.println(studentId);
        List<Sc> sc = scRepository.findByStudentId(studentId);
        String state = "published";
        for(int i=0;i<sc.size();i++){
            classid = sc.get(i).getClassId();
            List<Exam> exams = examRepository.findByStateClassId(classid,state);
           // List<Exam> exams = examRepository.findByClassId(classid);                  //deprecated
            examrs.addAll(exams);
        }
        m.put("examinfo",examrs);
        List<String> lstate = new ArrayList<String>();
        for(int i=0;i<examrs.size();i++){
            lstate.add(examstate(examrs.get(i).getExamId()));
        }
        m.put("state",lstate);
        return m;
    }

    @Override
    public boolean favorite(long questionId){
        long studentId = currentUser.getCurrentUser().getId();
        List<Answer> answers = answerRepository.findByQuestionIdAndStudentId(questionId,studentId);
        for(Answer answer : answers){
            answer.setFavorite(1);
            answerRepository.save(answer);
        }
        return true;
    }

    @Override
    public boolean unfavorite(long questionId){
        long studentId = currentUser.getCurrentUser().getId();
        List<Answer> answers = answerRepository.findByQuestionIdAndStudentId(questionId,studentId);
        for(Answer answer : answers){
            answer.setFavorite(0);
            answerRepository.save(answer);
        }
        return true;
    }

}

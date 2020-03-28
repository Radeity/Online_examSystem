package dhu.cst.ExamSystem.service;

import dhu.cst.ExamSystem.common.CheckResult;
import dhu.cst.ExamSystem.common.PaperResult;
import dhu.cst.ExamSystem.common.StuInfoResult;
import dhu.cst.ExamSystem.dao.*;
import dhu.cst.ExamSystem.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExamService {
    @Autowired
    private PaperRepository paperRepository;
    @Autowired
    private SCRepository scRepository;
    @Autowired
    private PdetailRepository pdRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private ScoreRepository scoreRepository;
    @Autowired
    private ECRepository ecRepository;
    @Autowired
    private StuRepository stuRepository;

    public Map getPaper(long examId){
        Map m = new HashMap();
        List<Paper> p = paperRepository.findByExamId(examId);
        if(p.size()!=0) {
            long paperId = (long) p.get(0).getPaperId();
            String paperName = (String) p.get(0).getPaperName();
            List<PaperResult> prs = pdRepository.queryPaperId(paperId);
            List<Paper> test = paperRepository.findAll();
            System.out.println(test.get(0).getPaperId());
            m.put("paperId",paperId);
            m.put("papername",paperName);
            m.put("question",prs);
            return m;
        }
        else return null;
    }

    public Map submit(Timestamp submit, List<Answer> answer){
        Map m = new HashMap();
        if(answer.size()!=0){
            long paperId = answer.get(0).getPaperId();
            long scorecal=0,sscore=0,snum=0,mscore=0,mnum=0,bscore=0,bnum=0,spoint=0,mpoint=0,bpoint=0,classId=0;
            List<Exam> examList = examRepository.findByPaperId(paperId);
            if(examList.size()!=0){
                Exam exam = examList.get(0);
                sscore = exam.getSingleScore();
                snum = exam.getSingleNum();
                mscore = exam.getMultiScore();
                mnum = exam.getMultiNum();
                bscore = exam.getBlankScore();
                bnum = exam.getBlankNum();
                if(snum!=0) spoint = sscore / snum;
                if(mnum!=0) mpoint = mscore / mnum;
                if(bnum!=0) bpoint = bscore / bnum;
            }
            for(int i=0;i<answer.size();i++){
                long questionId = answer.get(i).getQuestionId();
                List<CheckResult> check = questionRepository.queryQuestionId(questionId);
                String coranswer = check.get(0).getQuestionAnswer();
                answer.get(i).setCorAnswer(coranswer);
                long questionType = check.get(0).getQuestionType();
                if(answer.get(i).getStuAnswer().equals(coranswer)){
                    answer.get(i).setAnswerCheck("T");
                    System.out.println(questionType);

                    switch ((int) questionType){
                        case 0:
                            scorecal += spoint;
                            break;
                        case 1:
                            scorecal += mpoint;
                            break;
                        case 2:
                            System.out.println(scorecal);
                            scorecal += bpoint;
                            break;
                    }
                }
                else answer.get(i).setAnswerCheck("F");
                answerRepository.save(answer.get(i));
            }
            long subjectId = paperRepository.querySubjectId(paperId);
            long studentId = answer.get(0).getStudentId();
            List<StuInfoResult> lstu = stuRepository.queryforteabystuid(studentId);
            String studentName=null;
            if(lstu.size()!=0){
                studentName = lstu.get(0).getStudentName();
            }
            List<Sc> sc = scRepository.findByStudentId(studentId);
            if(sc.size()!=0){
                classId = sc.get(0).getClassId();
            }
            Score score = new Score();
            score.setClassId(classId);
            score.setSubjectId(subjectId);
            score.setPaperId(paperId);
            score.setScore(scorecal);
            score.setStudentId(studentId);
            score.setStudentName(studentName);
            score.setSubmit(submit);
            scoreRepository.save(score);
            m.put("submit","success");
            m.put("score",scorecal);
            return m;
        }
        m.put("submit","failed");
        return m;
    }

    public Map examstate(long studentId,long examId){
        Map m = new HashMap();
        List<Paper> p = paperRepository.findByExamId(examId);
        if(p.size()!=0) {
            long paperId = (long) p.get(0).getPaperId();
            List<Score> score = scoreRepository.findByStudentIdAndPaperId(studentId,paperId);
            if(score.size()!=0){
                m.put("state","finished");
            }
            else m.put("state","unfinished");
        }
        return m;
    }

    public Map paperdetail(long studentId, long examId) {
        Map m = new HashMap();
        List<Paper> p = paperRepository.findByExamId(examId);
        if(p.size()!=0) {
            long paperId = (long) p.get(0).getPaperId();
            System.out.println(paperId);
            List<Answer> answer= answerRepository.findByStudentIdAndPaperId(studentId,paperId);
            List<Exam> exam = examRepository.findByExamId(examId);
            m.put("exam",exam);
            m.put("answers",answer);
            return m;
        }
        return null;
    }

    public List<Exam> findExam(String ExamName, long studentId){
        long classid=0,examid=0;
        List<Long> longList = new ArrayList();
        List<Exam> lexam = examRepository.findAllByExamNameContaining(ExamName);
        List<Exam> examrs = new ArrayList<>();
        List<Sc> sc = scRepository.findByStudentId(studentId);

        for(int i=0;i<sc.size();i++){
            classid = sc.get(i).getClassId();
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

    public List<Exam> getExam(long studentId){
        long classid=0;
        List<Exam> examrs = new ArrayList<>();
        List<Sc> sc = scRepository.findByStudentId(studentId);
        for(int i=0;i<sc.size();i++){
            classid = sc.get(i).getClassId();
            List<Exam> exams = examRepository.findByClassId(classid);
            examrs.addAll(exams);
        }
        return examrs;
    }
}

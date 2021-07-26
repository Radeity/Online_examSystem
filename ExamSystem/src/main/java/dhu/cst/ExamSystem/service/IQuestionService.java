package dhu.cst.ExamSystem.service;

import dhu.cst.ExamSystem.entity.Pdetail;
import dhu.cst.ExamSystem.entity.Question;

import java.util.List;
import java.util.Map;

public interface IQuestionService {
    List<String> getbank(long subjectId);
    List<Question> getquestion(long subjectId, Integer page, Integer size);
    Map integratedquery(String bankName,long pointId,long type,Integer page,Integer size);
    List<Question> getquestionbybank(String bankName,Integer page,Integer size);
    List<Question> getquestionbypoint(long subjectId,long pointId,Integer page,Integer size);
    List<Question> getquestionbytype(long subjectId,long type,Integer page,Integer size);
    Map getquefromexam(long paperId, Integer page, Integer size);
    List<Pdetail> getquefromexambypoint(long paperId,long pointId,Integer page,Integer size);
    List<Pdetail> getquefromexambytype(long paperId,long type,Integer page,Integer size);
    Map addquestion(String queinfo);
    boolean saveall(List<Question> lq);
    Map delquestion(long questionid);
    boolean addbank(long subjectId,String bankname);
    boolean changebankname(String oldname,String newname);
    boolean delbank(String bankname);
    Map addquestiontoexam(long questionId, long paperId);
    Map delquefromexam(long questionid, long paperid);
    Map getquestionnum(long paperId);
    Map analyse(long ExamId);

}

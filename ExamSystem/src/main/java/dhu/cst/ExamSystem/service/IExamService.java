package dhu.cst.ExamSystem.service;

import dhu.cst.ExamSystem.entity.Answer;
import dhu.cst.ExamSystem.entity.Answerview;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface IExamService {
    Map getPaper(long examId);
    Map submit(Timestamp submit, List<Answer> answer);
    String examstate(long examId);
    Map paperdetail(long examId);
    Map paperdetail(long studentId,long examId);
    Map findExam(String ExamName);
    Map getExam();
    boolean favorite(long questionId);
    boolean unfavorite(long questionId);
    Map getfavorite();
    Answerview getquestion(long questionId);
}

package dhu.cst.ExamSystem.service;

import dhu.cst.ExamSystem.entity.Classinfo;
import dhu.cst.ExamSystem.entity.Exam;

import java.util.List;
import java.util.Map;

public interface IExamsetService {
    List<Classinfo> classQuery(long subjectid);
    List<Classinfo> classQuerybyteacher(long subjectId,long teacherId);
    Map regExam(String examinfo);
    Map delexam(long examid);
    List<Exam> findExam(String ExamName);
    boolean publish(long examid);
    boolean unpublish(long examid);

}

package dhu.cst.ExamSystem.service;

import dhu.cst.ExamSystem.entity.Exam;
import dhu.cst.ExamSystem.entity.Subject;
import dhu.cst.ExamSystem.entity.Teacherview;

import java.util.List;
import java.util.Map;

public interface ITeaService {
    List<Subject> findsubject();

    Map getExamdetails(long examid);

    List<Teacherview> teacherQuery(long subjectId);

    List<Exam> getExamlist(long subjectid);

    List<Exam> getpublishExam();

}

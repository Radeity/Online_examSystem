package dhu.cst.ExamSystem.service;

import dhu.cst.ExamSystem.entity.Answer;

import java.util.List;

public interface IAnswerService {
    List<Answer> getanswerbypaper(long paperid);
}

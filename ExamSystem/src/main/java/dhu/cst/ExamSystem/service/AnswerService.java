package dhu.cst.ExamSystem.service;

import dhu.cst.ExamSystem.dao.AnswerRepository;
import dhu.cst.ExamSystem.entity.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {
    @Autowired
    AnswerRepository answerRepository;

    public List<Answer> getanswerbypaper(long paperid){
        return answerRepository.findByPaperId(paperid);
    }


}

package dhu.cst.ExamSystem.service.Impl;

import dhu.cst.ExamSystem.dao.AnswerRepository;
import dhu.cst.ExamSystem.entity.Answer;
import dhu.cst.ExamSystem.service.IAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("AnswerService")
@Transactional
public class AnswerServiceImpl implements IAnswerService {
    @Autowired
    AnswerRepository answerRepository;

    @Override
    public List<Answer> getanswerbypaper(long paperid){
        return answerRepository.findByPaperId(paperid);
    }


}

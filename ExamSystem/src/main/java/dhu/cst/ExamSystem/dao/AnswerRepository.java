package dhu.cst.ExamSystem.dao;

import dhu.cst.ExamSystem.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByStudentIdAndPaperId(long studentId,long paperId);

    List<Answer> findByPaperId(long paperId);
}

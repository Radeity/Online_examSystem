package dhu.cst.ExamSystem.dao;

import dhu.cst.ExamSystem.common.AccuracyResult;
import dhu.cst.ExamSystem.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    //List<Answer> findByStudentIdAndPaperIdOrderByQ(long studentId,long paperId);

    List<Answer> findByQuestionId(long questionid);
    List<Answer> findByPaperId(long paperId);
    List<Answer> findByQuestionIdAndStudentId(long questionId, long studentId);

    @Query(value = "select new dhu.cst.ExamSystem.common.AccuracyResult(count(a.stuAnswer),a.stuAnswer) from Answer a where a.questionId=:questionId group by a.stuAnswer")
    List<AccuracyResult> findWrongAccuracy(long questionId);

    @Transactional
    void deleteByPaperId(long paperId);
}

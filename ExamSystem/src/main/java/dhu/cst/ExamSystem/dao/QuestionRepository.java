package dhu.cst.ExamSystem.dao;

import dhu.cst.ExamSystem.common.CheckResult;
import dhu.cst.ExamSystem.entity.Question;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Long> {

    @Query(value="select new dhu.cst.ExamSystem.common.CheckResult(questionType,questionAnswer)"
            +"from Question where questionId = :questionId")
    List<CheckResult> queryQuestionId(@Param("questionId") long questionId);

    List<Question> findBySubjectId(long subjectId);

    List<Question> findByQuestionId(long questionId);

    @Transactional
    void deleteByQuestionId(long questionId);
}

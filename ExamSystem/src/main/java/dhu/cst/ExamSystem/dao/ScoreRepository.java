package dhu.cst.ExamSystem.dao;

import dhu.cst.ExamSystem.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score,Long> {
    List<Score> findByStudentIdAndPaperId(long studentId, long paperId);

    List<Score> findByExamId(long ExamId);

    void deleteByExamId(long examId);
}

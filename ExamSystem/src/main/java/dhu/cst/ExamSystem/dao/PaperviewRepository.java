package dhu.cst.ExamSystem.dao;

import dhu.cst.ExamSystem.entity.Paperview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaperviewRepository extends JpaRepository<Paperview,Long> {
    List<Paperview> findByPaperIdOrderByQuestionType(long paperid);
}

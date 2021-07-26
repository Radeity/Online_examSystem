package dhu.cst.ExamSystem.dao;

import dhu.cst.ExamSystem.entity.Answerview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerviewRepository extends JpaRepository<Answerview,Long> {
    List<Answerview> findByStudentIdAndPaperIdOrderByQuestionType(long studentid,long paperid);
    List<Answerview> findByStudentIdAndFavorite(long studentid,int favorite);
    Answerview findByStudentIdAndQuestionId(long studentId, long questionId);
    //List<Answerview> findByPaperId(long paperid);
}

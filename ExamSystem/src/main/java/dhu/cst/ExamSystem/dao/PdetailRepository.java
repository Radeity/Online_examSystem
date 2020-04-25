package dhu.cst.ExamSystem.dao;

import dhu.cst.ExamSystem.entity.Pdetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface PdetailRepository extends JpaRepository<Pdetail, Long> {

    Page<Pdetail> findByPaperIdOrderByQuestionType(long paperId, Pageable page);

    int countByPaperId(long paperId);

    List<Pdetail> findByQuestionId(long questionId);

    Long countByPaperIdAndAndQuestionType(Long paperId,Long questionType);

    List<Pdetail> findByPaperIdAndQuestionId(long paperId,long questionId);

    Page<Pdetail> findByPaperIdAndPointIdOrderByQuestionType(long PaperId,long PointId,Pageable page);

    List<Pdetail> findByPaperIdAndPointIdOrderByQuestionType(long PaperId,long PointId);

    Page<Pdetail> findByPaperIdAndQuestionTypeOrderByQuestionType(long PaperId,long type,Pageable page);

    @Query(value = "select distinct(pd.pointId) from Pdetail pd where pd.paperId=:paperId")
    List<Long> finddistinctpoint(long paperId);

    void deleteByQuestionIdAndPaperId(long questionId,long paperId);
}

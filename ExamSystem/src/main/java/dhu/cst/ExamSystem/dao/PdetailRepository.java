package dhu.cst.ExamSystem.dao;

import dhu.cst.ExamSystem.common.PaperResult;
import dhu.cst.ExamSystem.entity.Pdetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
public interface PdetailRepository extends JpaRepository<Pdetail, Long> {
    @Query(value="select new dhu.cst.ExamSystem.common.PaperResult(questionId,questionType,opt,questionName)"
            +"from Pdetail where paperId = :paperId")
    List<PaperResult> queryPaperId(@Param("paperId") long paperId);

    List<Pdetail> findByPaperId(long paperId);

    Long countByPaperIdAndAndQuestionType(Long paperId,Long questionType);

    @Transactional
    void deleteByQuestionIdAndPaperId(long questionId,long paperId);
}

package dhu.cst.ExamSystem.dao;

import dhu.cst.ExamSystem.entity.Paper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaperRepository extends JpaRepository<Paper, Long> {

    @Query(value="select p from Paper p,Exam e where p.paperId = e.paperId and e.examId = :examId")
    List<Paper> findByExamId(long examId);

    @Query(value="select p.subjectId from Paper p where p.paperId = :paperId")
    long querySubjectId(@Param("paperId") long paperId);

    List<Paper> findBySubjectId(long subjectId);

    void deleteByPaperId(long paperId);

    List<Paper> findByPaperId(long paperid);

    List<Paper> findAllByPaperNameContaining(String papername);
}

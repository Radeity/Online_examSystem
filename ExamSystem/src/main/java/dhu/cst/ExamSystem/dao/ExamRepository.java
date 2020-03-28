package dhu.cst.ExamSystem.dao;

import dhu.cst.ExamSystem.entity.Exam;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Long> {

    @Query(value="select e from Exam e,Ec ec where ec.examId = e.examId and ec.classId = :classId")
    List<Exam> findByClassId(@Param("classId") long classId);

    List<Exam> findByPaperId(long paperId);

    List<Exam> findByExamId(long examId);

    List<Exam> findAllByExamNameContaining(String examname);

    @Query(value="select e from Exam e,Tc tc where tc.subjectId = e.subjectId and tc.teacherId = :teacherId")
    List<Exam> queryExamList(@Param("subjectId") long teacherId);

    @Transactional
    void deleteByExamId(long examId);

}

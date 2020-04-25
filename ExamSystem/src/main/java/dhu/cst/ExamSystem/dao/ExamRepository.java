package dhu.cst.ExamSystem.dao;

import dhu.cst.ExamSystem.entity.Exam;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Long> {

    @Query(value="select e from Exam e JOIN Ec ec ON ec.examId = e.examId where ec.classId = :classId")
    List<Exam> findByClassId(@Param("classId") long classId);

    @Query(value="select e from Exam e JOIN Ec ec ON ec.examId = e.examId where e.state=:state and ec.classId = :classId")
    List<Exam> findByStateClassId(long classId,String state);

    List<Exam> findByExamIdAndState(long ExamId,String state);

    List<Exam> findByPaperId(long paperId);

    List<Exam> findBySubjectId(long subjectid);

    List<Exam> findByExamId(long examId);

    @Query(value="select distinct e from Exam e,Classinfo tc where tc.subjectId = e.subjectId and tc.teacherId = :teacherId and e.state='published'")
    List<Exam> queryExamList(@Param("teacherId") long teacherId);

    List<Exam> findAllByExamNameContaining(String examname);

    void deleteByExamId(long examId);

}

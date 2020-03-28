package dhu.cst.ExamSystem.dao;

import dhu.cst.ExamSystem.entity.Classinfo;
import dhu.cst.ExamSystem.entity.Subject;
import dhu.cst.ExamSystem.entity.Tc;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TCRepository extends JpaRepository<Tc, Long> {
    @Query(value = "select s from Subject s,Tc tc where s.subjectId = tc.subjectId and tc.teacherId =:teacherId")
    List<Subject> findSubjectName(@Param("teacherId") long teacherId);

    @Query(value = "select c from Classinfo c,Tc tc where tc.classId = c.classId and tc.subjectId =:subjectId")
    List<Classinfo> findClassId(@Param("subjectId") long subjectId);

    List<Tc> findByTeacherId(long teacherId);

}

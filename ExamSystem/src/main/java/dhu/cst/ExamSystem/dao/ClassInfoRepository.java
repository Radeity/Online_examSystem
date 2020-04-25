package dhu.cst.ExamSystem.dao;

import dhu.cst.ExamSystem.entity.Classinfo;
import dhu.cst.ExamSystem.entity.Subject;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClassInfoRepository extends JpaRepository<Classinfo,Long> {

    List<Classinfo> findBySubjectId(long subjectId);
    List<Classinfo> findBySubjectIdAndTeacherId(long subjectId,long teacherId);
    List<Classinfo> findByTeacherId(long teacherId);
    Classinfo findByClassId(long classId);

    @Query(value = "select distinct s from Subject s,Classinfo c where s.subjectId = c.subjectId and c.teacherId =:teacherId")
    List<Subject> findSubjectName(@Param("teacherId") long teacherId);

    List<Classinfo> findByClassName(String classname);
}

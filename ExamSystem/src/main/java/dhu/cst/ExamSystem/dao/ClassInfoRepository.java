package dhu.cst.ExamSystem.dao;

import dhu.cst.ExamSystem.common.ClassResult;
import dhu.cst.ExamSystem.entity.Classinfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClassInfoRepository extends JpaRepository<Classinfo,Long> {
    @Query(value = "select new dhu.cst.ExamSystem.common.ClassResult(c.classId,c.className)"
                +"from Classinfo c,Tc tc where tc.classId = c.classId and tc.teacherId =:teacherId")
    List<ClassResult> findClassId(@Param("teacherId") long teacherId);

    List<Classinfo> findByClassName(String classname);
}

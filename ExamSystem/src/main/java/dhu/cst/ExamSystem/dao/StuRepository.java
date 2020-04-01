package dhu.cst.ExamSystem.dao;

import dhu.cst.ExamSystem.common.StuInfoResult;
import dhu.cst.ExamSystem.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StuRepository extends JpaRepository<Student, Long> {
    List<Student> findByStudentId(long Id);
    List<Student> findByStudentNoAndStudentPwd(String name ,String password);
    List<Student> findAllByStudentNoContaining(long StudentNo);
    List<Student> findAllByStudentNameContaining(String StudentName);

    @Transactional
    void deleteByStudentName(String name);

    @Query(value="select new dhu.cst.ExamSystem.common.StuInfoResult(studentId,studentNo,studentName)"
            +"from Student where studentId = :studentId")
    List<StuInfoResult> queryforteabystuid(long studentId);
}

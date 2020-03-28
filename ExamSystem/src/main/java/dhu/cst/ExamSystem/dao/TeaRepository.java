package dhu.cst.ExamSystem.dao;

import dhu.cst.ExamSystem.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TeaRepository extends JpaRepository<Teacher, Long>{
    List<Teacher> findByTeacherNoAndTeacherPwd(String name, String password);
    List<Teacher> findAllByTeacherNoContaining(long teacherNo);
    List<Teacher> findAllByTeacherNameContaining(String teacherName);
    @Transactional
    void deleteByTeacherId(long teaId);
}

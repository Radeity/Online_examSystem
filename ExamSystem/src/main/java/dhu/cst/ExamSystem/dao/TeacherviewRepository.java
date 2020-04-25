package dhu.cst.ExamSystem.dao;

import dhu.cst.ExamSystem.entity.Teacherview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherviewRepository extends JpaRepository<Teacherview,Long> {
    List<Teacherview> findBySubjectId(long subjectId);

}

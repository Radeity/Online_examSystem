package dhu.cst.ExamSystem.dao;

import dhu.cst.ExamSystem.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject,Long> {
    Subject findBySubjectName(String subjectName);
}

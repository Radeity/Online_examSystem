package dhu.cst.ExamSystem.dao;


import dhu.cst.ExamSystem.entity.Sc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SCRepository extends JpaRepository<Sc, Long> {
    List<Sc> findByStudentId(long Id);
}

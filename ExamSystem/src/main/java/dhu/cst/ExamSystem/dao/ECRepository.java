package dhu.cst.ExamSystem.dao;

import dhu.cst.ExamSystem.entity.Ec;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ECRepository extends JpaRepository<Ec,Long> {
    List<Ec> findByExamId(long examid);
}

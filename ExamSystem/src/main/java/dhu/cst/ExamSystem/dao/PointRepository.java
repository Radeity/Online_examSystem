package dhu.cst.ExamSystem.dao;

import dhu.cst.ExamSystem.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointRepository extends JpaRepository<Point,Long> {

    List<Point> findBySubjectId(long subjectId);

    void deleteByPointId(long pointId);
}

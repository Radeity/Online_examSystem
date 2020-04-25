package dhu.cst.ExamSystem.dao;

import dhu.cst.ExamSystem.entity.Scoreview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScoreviewRepository extends JpaRepository<Scoreview,Long> {
    //List<Scoreview> findByStudentIdAndPaperId(long studentId,long paperId);

    @Query(value = "select * from Scoreview where if(?1 !='',subject_name=?1,1=1) and if(?2 !='',exam_name=?2,1=1)and if(?3 !='',teacher_name=?3,1=1) ",nativeQuery = true)
    Page<Scoreview> integratedquery(String subjectName,String examName,String teacherName,Pageable page);

    List<Scoreview> findAllByStudentNameContaining(String StudentName);

    Page<Scoreview> findBySubjectId(long subjectId, Pageable page);

    Page<Scoreview> findByClassId(long classId,Pageable page);

    List<Scoreview> findByStudentId(long studentid);

    @Query(value = "select * from Scoreview where if(?1 !=0,class_id=?1,1=1) and if(?2!=0,exam_id=?2,1=1) ",nativeQuery = true)
    List<Scoreview> findByClassIdAndExamId(long classId,long ExamId);
}

package dhu.cst.ExamSystem.dao;

import dhu.cst.ExamSystem.entity.Question;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {

    Page<Question> findBySubjectIdAndPointIdIsNotNull(long subjectId, Pageable page);

    Page<Question> findByBankNameAndPointIdIsNotNull(String bankname, Pageable page);

    List<Question> findByQuestionIdAndPointIdIsNotNull(long questionId);

    Page<Question> findBySubjectIdAndPointId(long subjectId,long pointId, Pageable page);

    Page<Question> findBySubjectIdAndQuestionType(long subjectId,long type, Pageable page);

    @Query(value = "select q.bankName from Question q where q.pointId is null and q.subjectId=:subjectId")
    List<String> getbanks(@Param("subjectId") long subjectId);

    @Query(value = "select * from Question where if(?1 !='',bank_name=?1,1=1) and if(?2!=0,point_id=?2,1=1)" +
            "and if(?3 !=-1,question_type=?3,1=1 and point_id is not null) ",nativeQuery = true)
    Page<Question> integratedquery(String bankName,long pointId,long type,Pageable page);

    @Query(value = "select count(*) from Question where if(?1 !='',bank_name=?1,1=1) and if(?2!=0,point_id=?2,1=1)" +
            "and if(?3 !=-1,question_type=?3,1=1 and point_id is not null) ",nativeQuery = true)
    Integer integratedquery(String bankName,long pointId,long type);

    @Modifying
    @Query(value="insert into question(subject_id,bank_name) values(?1,?2)",nativeQuery = true)
    void savebank(long subjectId,String bankname);

    @Modifying
    @Query(value="update Question q set q.bank_name=:newname where q.bank_name=:oldname",nativeQuery = true)
    void updatebankname(String oldname,String newname);


    @Modifying
    @Query(value="delete from Question where bank_name=?1",nativeQuery = true)
    void deleteByBankName(String bankname);

    void deleteByQuestionId(long questionId);
}

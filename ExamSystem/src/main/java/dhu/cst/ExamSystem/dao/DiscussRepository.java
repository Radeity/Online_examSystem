package dhu.cst.ExamSystem.dao;

import dhu.cst.ExamSystem.entity.Discuss;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiscussRepository extends JpaRepository<Discuss,Long> {
    @Query(value="select d from Discuss d where d.reply=0 and d.questionId=:questionId")
    List<Discuss> findByQuestionId(@Param("questionId") long questionId);

    @Query(value="select d from Discuss d where d.reply=0 and d.questionId=:questionId order by d.dtime DESC ")
    List<Discuss> findByQuestionIdOrderByDtimeDesc(@Param("questionId") long questionId);

    @Query(value="select d from Discuss d where d.reply=0 and d.questionId=:questionId order by d.thumb DESC ")
    List<Discuss> findByQuestionIdOrderByThumbDesc(@Param("questionId") long questionId);

    @Query(value="select d from Discuss d where d.reply=1 and d.replydiscuss=:discussId order by d.dtime DESC ")
    List<Discuss> findReplyByDiscussId(@Param("discussId") long discussId);

    @Query(value = "select count(d) from Discuss d where d.replydiscuss = :discussId ")
    Long countByDiscussId(@Param("discussId") long discussId);

    @Query(value = "select count(d) from Discuss d where d.replydiscuss = :discussId order by d.thumb DESC")
    Long countByDiscussIdOrderByThumbDesc(@Param("discussId") long discussId);

    @Query(value = "select count(d) from Discuss d where d.replydiscuss = :discussId order by d.dtime DESC")
    Long countByDiscussIdOrderByDtimeDesc(@Param("discussId") long discussId);

    Discuss findByDiscussId(long discussId);

    void deleteByDiscussId(long paperId);

    Page<Discuss> findAll(Pageable page);
}

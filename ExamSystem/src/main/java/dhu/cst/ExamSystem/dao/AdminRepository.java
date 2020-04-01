package dhu.cst.ExamSystem.dao;

import dhu.cst.ExamSystem.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {
    List<Admin> findByAdminNoAndAdminPwd(String name , String password);
}

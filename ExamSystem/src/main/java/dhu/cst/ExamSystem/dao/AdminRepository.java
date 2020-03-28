package dhu.cst.ExamSystem.dao;

import dhu.cst.ExamSystem.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    List<Admin> findByAdminNoAndAdminPwd(String name , String password);

}

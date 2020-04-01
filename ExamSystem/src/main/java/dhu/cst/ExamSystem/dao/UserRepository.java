package dhu.cst.ExamSystem.dao;

import dhu.cst.ExamSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByName(String name);
    Optional<User> findByRoles(String role);

    @Transactional
    void deleteByName( String name);

}

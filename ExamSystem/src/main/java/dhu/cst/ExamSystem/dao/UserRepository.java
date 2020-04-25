package dhu.cst.ExamSystem.dao;

import dhu.cst.ExamSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByName(String name);
    Optional<User> findByRoles(String role);

    @Transactional
    void deleteByName( String name);

=======
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameAndState(String username,long state);
    Optional<User> findByName(String name);
    List<User> findByRoles(String role);
    User findByUsername(String username);
    @Transactional
    void deleteByName( String name);
    User findByUsernameAndPassword(String username,String password);
    User findById(long id);

    @Query(value="select user from User user JOIN Sc sc ON sc.studentId = user.id where sc.classId = :classId")
    List<User> findByClassId(long classId);
>>>>>>> dev
}

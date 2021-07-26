package dhu.cst.ExamSystem.service;

import dhu.cst.ExamSystem.entity.User;
import dhu.cst.ExamSystem.utils.RoleUtil;

import java.util.List;
import java.util.Map;

public interface IAdminUserService {
    void saveUser(Map<String, String> registerUser);
    User findUserByUserName(String username);
    User findUserByName(String name);
    List<User> findUserByRole(String role);
    List<User> getAllUser();
    RoleUtil getuser();
    void deleteUserByUserName(String name);
    boolean changePass(String username,String oldpassword,String newpassword);
    boolean stateforbid(long id);
    boolean statepermit(long id);

}

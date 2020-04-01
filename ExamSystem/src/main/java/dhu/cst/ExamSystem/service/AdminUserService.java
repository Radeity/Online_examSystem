package dhu.cst.ExamSystem.service;

import dhu.cst.ExamSystem.dao.StuRepository;
import dhu.cst.ExamSystem.dao.TeaRepository;
import dhu.cst.ExamSystem.dao.UserRepository;
import dhu.cst.ExamSystem.entity.Student;
import dhu.cst.ExamSystem.entity.Teacher;
import dhu.cst.ExamSystem.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AdminUserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private StuRepository stuRepository;
    @Autowired
    private TeaRepository teaRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public void saveUser(Map<String, String> registerUser) {
        /*Optional<User> optionalUser = userRepository.findByUsername(registerUser.get("username"));
        if (optionalUser.isPresent()) {
            throw new UserNameAlreadyExistException("User name already exist!Please choose another user name.");
        }*/
        Integer id;
        User user = new User();
        if(registerUser.get("id")!=null) {
            id = Integer.valueOf(registerUser.get("id"));
            user.setId(id);
        }
        String role = registerUser.get("role");
        String pwd = registerUser.get("password");
        String username = registerUser.get("username");
        String name = registerUser.get("name");
        user.setRoles(role);
        user.setName(name);
        user.setUsername(username);
        user.setPassword(bCryptPasswordEncoder.encode(registerUser.get("password")));
        //user.setPassword(pwd);
        userRepository.save(user);
        switch (role){
            case "TEA":
                Teacher tea = new Teacher();
                tea.setTeacherNo(username);
                tea.setTeacherPwd(pwd);
                tea.setTeacherName(name);
                teaRepository.save(tea);
                break;
            case "STU":
                Student stu = new Student();
                stu.setStudentName(name);
                stu.setStudentPwd(pwd);
                stu.setStudentNo(username);
                stuRepository.save(stu);
                break;
        }
    }

    public User findUserByUserName(String name) {
        return userRepository.findByUsername(name)
                .orElseThrow(() -> new UsernameNotFoundException("No user found with number " + name));
    }

    public User findUserByName(String name) {
        return userRepository.findByName(name)
                .orElseThrow(() -> new UsernameNotFoundException("No user found with name " + name));
    }

    public User findUserByRole(String role){
        return userRepository.findByRoles(role)
                .orElseThrow(() -> new UsernameNotFoundException("No user found with role " + role));
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public void deleteUserByUserName(String name) {
        Optional<User> ouser = userRepository.findByName(name);
        User user = ouser.get();
        List<SimpleGrantedAuthority> lrole = user.getRoles();
        String role = lrole.get(0).getAuthority();
        System.out.println(role);
        if(role.equals("ROLE_STU")){
            stuRepository.deleteByStudentName(name);
        }
        else if(role.equals("ROLE_TEA")){
            teaRepository.deleteByTeacherName(name);
        }
        userRepository.deleteByName(name);

    }
}

package dhu.cst.ExamSystem.service.Impl;

import dhu.cst.ExamSystem.dao.UserRepository;
import dhu.cst.ExamSystem.entity.User;
import dhu.cst.ExamSystem.service.IAdminUserService;
import dhu.cst.security.entity.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("AdminUserService")
@Transactional
public class AdminUserServiceImpl implements IAdminUserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private CurrentUser currentUser;

    @Override
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
        String department = registerUser.get("department");
        String adminclass = registerUser.get("adminclass");
        String gender = registerUser.get("gender");
        long state = Long.parseLong(registerUser.get("state"));
        user.setState(state);
        user.setGender(gender);
        user.setRoles(role);
        user.setName(name);
        user.setUsername(username);
        if(pwd.length()>50){
            user.setPassword(pwd);
        }
        else{
            user.setPassword(bCryptPasswordEncoder.encode(pwd));
        }
        user.setDepartment(department);
        user.setAdminclass(adminclass);
        userRepository.save(user);
    }

    @Override
    public User findUserByUserName(String name) {
        long state=1;
        return userRepository.findByUsernameAndState(name,state)
                .orElseThrow(() -> new UsernameNotFoundException("No user found with number " + name));
    }
    @Override
    public User findUserByName(String name) {
        return userRepository.findByName(name)
                .orElseThrow(() -> new UsernameNotFoundException("No user found with name " + name));
    }
    @Override
    public List<User> findUserByRole(String role){
        return userRepository.findByRoles(role);
    }
    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
    @Override
    public User getuser(){
        long Id = currentUser.getCurrentUser().getId();
        return userRepository.findById(Id);
    }
    @Override
    public void deleteUserByUserName(String name) {
        Optional<User> ouser = userRepository.findByName(name);
        User user = ouser.get();
        List<SimpleGrantedAuthority> lrole = user.getRoles();
        String role = lrole.get(0).getAuthority();
        System.out.println(role);
        userRepository.deleteByName(name);
    }
    @Override
    public boolean changePass(String username,String oldpassword,String newpassword){
        long userId = currentUser.getCurrentUser().getId();
        User usr = userRepository.findByUsername(username);
        if(usr==null){
            return false;
        }
        String pwd = usr.getPassword();
        if(!bCryptPasswordEncoder.matches(oldpassword,pwd)){
            return false;
        }
        usr.setPassword(bCryptPasswordEncoder.encode(newpassword));
        userRepository.save(usr);
        return true;
    }
    @Override
    public boolean stateforbid(long id){
        User user = userRepository.findById(id);
        user.setState(0);
        userRepository.save(user);
        return true;
    }
    @Override
    public boolean statepermit(long id){
        User user = userRepository.findById(id);
        user.setState(1);
        userRepository.save(user);
        return true;
    }
}

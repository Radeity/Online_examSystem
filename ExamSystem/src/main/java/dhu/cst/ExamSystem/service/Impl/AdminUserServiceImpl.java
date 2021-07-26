package dhu.cst.ExamSystem.service.Impl;

import dhu.cst.ExamSystem.dao.UserRepository;
import dhu.cst.ExamSystem.entity.User;
import dhu.cst.ExamSystem.service.IAdminUserService;
import dhu.cst.ExamSystem.utils.RedisUtil;
import dhu.cst.ExamSystem.utils.RoleUtil;
import dhu.cst.security.entity.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("AdminUserService")
public class AdminUserServiceImpl implements IAdminUserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private CurrentUser currentUser;
    @Autowired
    private RedisUtil redisUtil;


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
        redisUtil.set("user"+username,user);
    }

    @Override
    public User findUserByUserName(String username) {
        long state=1;
        User user;
        String key = "user"+username;
        if(redisUtil.hasKey(key)){
            user = (User)redisUtil.get(key);
        }
        else{
            user = userRepository.findByUsernameAndState(username, state);
            System.out.println("from db");
            redisUtil.set(key,user);
        }
        return user;
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
    public RoleUtil getuser(){
        long Id = currentUser.getCurrentUser().getId();
        User user = userRepository.findById(Id);
        RoleUtil roleUtil = new RoleUtil(user);
        System.out.println(roleUtil.getName());
        return roleUtil;
    }
    @Override
    public void deleteUserByUserName(String name) {
        Optional<User> ouser = userRepository.findByName(name);
        User user = ouser.get();
        RoleUtil roleUtil = new RoleUtil(user);
        List<SimpleGrantedAuthority> lrole = roleUtil.getRolesforauth(user);
        String role = lrole.get(0).getAuthority();
        redisUtil.del("user"+user.getUsername());
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
        redisUtil.del("user"+user.getUsername());
        userRepository.save(user);
        return true;
    }
    @Override
    public boolean statepermit(long id){
        User user = userRepository.findById(id);
        user.setState(1);
        userRepository.save(user);
        redisUtil.set("user"+user.getUsername(),user);
        return true;
    }
}

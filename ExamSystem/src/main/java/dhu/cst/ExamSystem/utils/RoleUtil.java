package dhu.cst.ExamSystem.utils;

import dhu.cst.ExamSystem.dao.UserRepository;
import dhu.cst.ExamSystem.entity.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class RoleUtil {
    @Autowired
    UserRepository userRepository;

    private long id;
    private String username;
    private String name;
    private List<SimpleGrantedAuthority> roles;
    private String password;
    private String adminclass;
    private String gender;
    private String department;
    private long state;

    public RoleUtil(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.name = user.getName();
        this.roles = getRolesforauth(user);
        this.password = user.getPassword();
        this.adminclass = user.getAdminclass();
        this.gender = user.getGender();
        this.department = user.getDepartment();
        this.state = user.getState();
    }

    public List<SimpleGrantedAuthority> getRolesforauth(User user) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        String roles = user.getRoles();
        Arrays.stream(roles.split(",")).forEach(role ->
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role)));
        return authorities;
    }
}

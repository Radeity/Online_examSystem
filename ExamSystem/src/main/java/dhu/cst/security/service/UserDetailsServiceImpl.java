package dhu.cst.security.service;

import dhu.cst.ExamSystem.entity.User;
import dhu.cst.ExamSystem.service.AdminUserService;
import dhu.cst.security.entity.JwtUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AdminUserService userService;

    public UserDetailsServiceImpl(AdminUserService userService) {
        this.userService = userService;
    }
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userService.findUserByUserName(name);
        return new JwtUser(user);
    }

}

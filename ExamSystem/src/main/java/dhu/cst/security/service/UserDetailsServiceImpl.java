package dhu.cst.security.service;

import dhu.cst.ExamSystem.entity.User;
<<<<<<< HEAD
import dhu.cst.ExamSystem.service.AdminUserService;
import dhu.cst.security.entity.JwtUser;
=======
import dhu.cst.ExamSystem.service.IAdminUserService;
import dhu.cst.security.entity.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> dev
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

<<<<<<< HEAD
    private final AdminUserService userService;

    public UserDetailsServiceImpl(AdminUserService userService) {
        this.userService = userService;
    }
=======
    @Autowired
    IAdminUserService userService;

>>>>>>> dev
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userService.findUserByUserName(name);
        return new JwtUser(user);
    }
<<<<<<< HEAD

=======
>>>>>>> dev
}

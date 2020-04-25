package dhu.cst.security.entity;

import dhu.cst.security.service.UserDetailsServiceImpl;
<<<<<<< HEAD
=======
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> dev
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CurrentUser {

<<<<<<< HEAD
    private final UserDetailsServiceImpl userDetailsService;

    public CurrentUser(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public dhu.cst.security.entity.JwtUser getCurrentUser() {
        return (dhu.cst.security.entity.JwtUser) userDetailsService.loadUserByUsername(getCurrentUserName());
=======
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    public JwtUser getCurrentUser() {
        return (JwtUser) userDetailsService.loadUserByUsername(getCurrentUserName());
>>>>>>> dev
    }

    private static String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null) {
            return (String) authentication.getPrincipal();
        }
        return null;
    }
}

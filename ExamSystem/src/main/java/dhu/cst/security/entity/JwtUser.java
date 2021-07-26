package dhu.cst.security.entity;

import dhu.cst.ExamSystem.entity.User;
import dhu.cst.ExamSystem.utils.RoleUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class JwtUser implements UserDetails {

    private long id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtUser() {
    }

    //通过User创建JwtUser
    public JwtUser(User user) {
        id = user.getId();
        username = user.getUsername();
        password = user.getPassword();
        RoleUtil roleUtil = new RoleUtil(user);
        authorities = roleUtil.getRolesforauth(user);
    }

    public long getId(){return id;}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "JwtUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + authorities +
                '}';
    }

}

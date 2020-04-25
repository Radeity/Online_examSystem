package dhu.cst.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import dhu.cst.security.constants.SecurityConstants;
import dhu.cst.security.entity.JwtUser;
import dhu.cst.security.entity.LoginUser;
import dhu.cst.security.utils.JwtTokenUtils;
import org.springframework.security.authentication.AuthenticationManager;
<<<<<<< HEAD
=======
import org.springframework.security.authentication.AuthenticationServiceException;
>>>>>>> dev
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
<<<<<<< HEAD
=======
import org.springframework.util.StringUtils;
>>>>>>> dev

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private ThreadLocal<Boolean> rememberMe = new ThreadLocal<>();
    private AuthenticationManager authenticationManager;
<<<<<<< HEAD
=======

>>>>>>> dev
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        // 设置URL，以确定是否需要身份验证
        super.setFilterProcessesUrl(SecurityConstants.AUTH_LOGIN_URL);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
<<<<<<< HEAD
                                                HttpServletResponse response) throws AuthenticationException {
=======
                                                HttpServletResponse response) throws AuthenticationServiceException {
>>>>>>> dev

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // 从输入流中获取到登录的信息
            LoginUser loginUser = objectMapper.readValue(request.getInputStream(), LoginUser.class);
<<<<<<< HEAD
            rememberMe.set(loginUser.getRememberMe());
            // 这部分和attemptAuthentication方法中的源码是一样的，
            // 只不过由于这个方法源码的是把用户名和密码这些参数的名字是死的，所以我们重写了一下
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                    loginUser.getUsername(), loginUser.getPassword());
=======
            String requestCaptcha = loginUser.getCode();
            String genCaptcha = (String) request.getSession().getAttribute("index_code");
            //System.out.println(requestCaptcha+" "+genCaptcha);
            if (StringUtils.isEmpty(requestCaptcha))
                throw new AuthenticationServiceException("验证码不能为空!");
            if (!genCaptcha.toLowerCase().equals(requestCaptcha.toLowerCase())) {
                throw new AuthenticationServiceException("验证码错误!");
            }
            request.getSession().invalidate();
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                    loginUser.getUsername(), loginUser.getPassword());

>>>>>>> dev
            return authenticationManager.authenticate(authRequest);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 如果验证成功，就生成token并返回
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authentication) {
        JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
        List<String> roles = jwtUser.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        // 创建 Token
<<<<<<< HEAD
        String token = JwtTokenUtils.createToken(jwtUser.getUsername(), roles, rememberMe.get());
=======
        String token = JwtTokenUtils.createToken(jwtUser.getUsername(), roles);
>>>>>>> dev
        // Http Response Header 中返回 Token
        response.setHeader(SecurityConstants.TOKEN_HEADER, token);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException {
<<<<<<< HEAD
=======
        //System.out.println(authenticationException.getMessage());
>>>>>>> dev
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authenticationException.getMessage());
    }
}

package dhu.cst.ExamSystem.controller;

import dhu.cst.ExamSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserLoginController {

    /*Cookie cookie;
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public Result Login(HttpServletResponse response, HttpServletRequest request,
                        @RequestParam("name") String name,
                        @RequestParam("password") String password) throws ServletException {
        List<Student> stu =  userService.check(name,password);
        if(stu.size()==0){
            throw new ServletException("login failed!");
        }
        return new Result(JwtUtil.getToken(name));

    }



    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    public void logout( HttpServletRequest request){
        HttpSession session=request.getSession();
        session.invalidate();
    }*/
    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    public Map login(@RequestBody Map params) {
        String username = (String) params.get("username");
        String password = (String) params.get("password");
        return userService.login(username, password);
    }

    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request) {
        return userService.logout(request);
    }

}

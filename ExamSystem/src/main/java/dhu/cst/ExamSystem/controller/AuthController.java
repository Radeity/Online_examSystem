package dhu.cst.ExamSystem.controller;

import dhu.cst.ExamSystem.entity.User;
import dhu.cst.ExamSystem.service.IAdminUserService;
import dhu.cst.ExamSystem.utils.RoleUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "/auth/** 的接口")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    IAdminUserService userService;

    @ApiOperation("插入或修改用户信息")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/saveorupdateuser")
    public ResponseEntity registerUser(@RequestBody Map<String, String> registerUser) {
        userService.saveUser(registerUser);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("获取全部用户信息")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUser() {
    //    System.out.println("当前访问该接口的用户为：" + currentUser.getCurrentUser().toString());
        List<User> allUser = userService.getAllUser();
        return ResponseEntity.ok().body(allUser);
    }

    @ApiOperation("根据名字查找用户信息")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/finduser")
    public ResponseEntity<User> findUserByName(@RequestParam("name") String name) {
        //    System.out.println("当前访问该接口的用户为：" + currentUser.getCurrentUser().toString());
        User user = userService.findUserByName(name);
        return ResponseEntity.ok().body(user);
    }

    @ApiOperation("根据学号查找用户信息")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/getuserinfo")
    public ResponseEntity<User> findUserByUserName(@RequestParam("username") String username) {
        //    System.out.println("当前访问该接口的用户为：" + currentUser.getCurrentUser().toString());
        User user = userService.findUserByUserName(username);
        return ResponseEntity.ok().body(user);
    }

    @ApiOperation("用户状态禁用")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/stateforbid")
    public boolean stateforbid(@RequestBody Map params){
        long id = (int) params.get("id");
        return userService.stateforbid(id);
    }

    @ApiOperation("用户状态启用")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/statepermit")
    public boolean statepermit(@RequestBody Map params){
        long id = (int)params.get("id");
        return userService.statepermit(id);
    }


    @ApiOperation("修改密码")
    @PostMapping(value = "/changePass")
    public boolean stuchangePass(@RequestBody Map params) {
        String username = (String) params.get("username");
        String oldpassword = (String) params.get("oldpassword");
        String newpassword =  (String) params.get("newpassword");
        return userService.changePass(username,oldpassword,newpassword);
    }

    @ApiOperation("获取登录信息")
    @GetMapping(value = "/getuser")
    public RoleUtil getcurrentuser() {
        return userService.getuser();
    }

    @ApiOperation("根据角色查找用户信息")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/userbyrole")
    public ResponseEntity<List<User>> findUserByRole(@RequestParam("role") String role) {
        //    System.out.println("当前访问该接口的用户为：" + currentUser.getCurrentUser().toString());
        List<User> user = userService.findUserByRole(role);
        return ResponseEntity.ok().body(user);
    }

    @ApiOperation("删除用户信息")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("/deleteuser")
    public ResponseEntity<User> deleteUser(@RequestParam("username") String username) {
        //    System.out.println("当前访问该接口的用户为：" + currentUser.getCurrentUser().toString());
        userService.deleteUserByUserName(username);
        return ResponseEntity.ok().build();
    }

}

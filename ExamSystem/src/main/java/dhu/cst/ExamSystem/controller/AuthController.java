package dhu.cst.ExamSystem.controller;

import dhu.cst.ExamSystem.entity.User;
import dhu.cst.ExamSystem.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AdminUserService userService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/saveorupdateuser")
    public ResponseEntity registerUser(@RequestBody Map<String, String> registerUser) {
        userService.saveUser(registerUser);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUser() {
    //    System.out.println("当前访问该接口的用户为：" + currentUser.getCurrentUser().toString());
        List<User> allUser = userService.getAllUser();
        return ResponseEntity.ok().body(allUser);
    }

    @GetMapping("/finduser")
    public ResponseEntity<User> findUserByName(@RequestParam("name") String name) {
        //    System.out.println("当前访问该接口的用户为：" + currentUser.getCurrentUser().toString());
        User user = userService.findUserByName(name);
        return ResponseEntity.ok().body(user);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/userbyrole")
    public ResponseEntity<User> findUserByRole(@RequestParam("role") String role) {
        //    System.out.println("当前访问该接口的用户为：" + currentUser.getCurrentUser().toString());
        User user = userService.findUserByRole(role);
        return ResponseEntity.ok().body(user);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/deleteuser")
    public ResponseEntity<User> deleteUser(@RequestParam("username") String username) {
        //    System.out.println("当前访问该接口的用户为：" + currentUser.getCurrentUser().toString());
        userService.deleteUserByUserName(username);
        return ResponseEntity.ok().build();
    }

}

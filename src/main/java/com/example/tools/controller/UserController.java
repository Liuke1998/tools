package com.example.tools.controller;

import com.example.tools.entry.User;
import com.example.tools.exception.BusinessException;
import com.example.tools.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    /**
     * 注册
     * @param user
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody User user){
        try {
            userService.register(user);
            return ResponseEntity.ok("注册成功");
        } catch (BusinessException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user){
        try {
            userService.login(user);
            return ResponseEntity.ok("登录成功");
        } catch (BusinessException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

package com.example.tools.controller;

import com.example.tools.anno.NotNeedLogin;
import com.example.tools.entry.User;
import com.example.tools.exception.BusinessException;
import com.example.tools.response.RestResponse;
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
    @NotNeedLogin
    public RestResponse register(@RequestBody User user){
        try {
            userService.register(user);
            return RestResponse.createSuccessResult();
        } catch (BusinessException e){
            return RestResponse.createResult(401, e.getMessage());
        }
    }

    @PostMapping("/login")
    @NotNeedLogin
    public RestResponse login(@RequestBody User user){
        try {
            userService.login(user);
            return RestResponse.createSuccessResult();
        } catch (BusinessException e){
            return RestResponse.createResult(402, e.getMessage());
        }
    }
}

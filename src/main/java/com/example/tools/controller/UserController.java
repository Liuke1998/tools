package com.example.tools.controller;

import com.example.tools.anno.NotNeedLogin;
import com.example.tools.config.properties.MailProperties;
import com.example.tools.entry.User;
import com.example.tools.exception.BusinessException;
import com.example.tools.response.RestResponse;
import com.example.tools.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

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

    @Autowired
    private MailProperties mailProperties;

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/sendMsg")
    public RestResponse sendMsg(){
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message);
            helper.setFrom(mailProperties.getUsername());
            helper.setTo("1904428276@qq.com");
            helper.setSubject("测试");
            helper.setText("测试邮件", true);
            mailSender.send(message);
            return RestResponse.createSuccessResult();
        }catch (Exception e){
            e.printStackTrace();
            return RestResponse.createErrorResult(400, "发送失败");
        }
    }
}

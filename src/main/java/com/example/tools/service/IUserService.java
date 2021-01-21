package com.example.tools.service;

import com.example.tools.entry.User;

public interface IUserService {
    //注册
    void register(User user);
    //登录
    void login(User user);
}

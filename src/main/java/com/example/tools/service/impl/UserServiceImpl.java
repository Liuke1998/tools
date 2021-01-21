package com.example.tools.service.impl;

import com.example.tools.entry.User;
import com.example.tools.exception.BusinessException;
import com.example.tools.mapper.UserMapper;
import com.example.tools.service.IUserService;
import com.example.tools.util.SessionUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public void register(User user) {
        User selectUser = userMapper.selectByUsername(user.getUsername());
        if (selectUser != null){
            throw new BusinessException("用户名已存在");
        }
        userMapper.insertSelective(user);
    }

    @Override
    public void login(User user) {
        User selectUser = userMapper.selectByUsername(user.getUsername());
        if (selectUser == null){
            throw new BusinessException("用户不存在");
        }
        if (!selectUser.getPassword().equals(user.getPassword())){
            throw new BusinessException("密码错误");
        }
        selectUser.setLastLoginDate(new Date());
        SessionUtil.setLoginInfo(selectUser);
        userMapper.updateByPrimaryKeySelective(selectUser);
    }
}

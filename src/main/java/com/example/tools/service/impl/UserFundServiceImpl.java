package com.example.tools.service.impl;

import com.example.tools.entry.User;
import com.example.tools.entry.UserFund;
import com.example.tools.mapper.UserFundMapper;
import com.example.tools.service.IUserFundService;
import com.example.tools.util.SessionUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class UserFundServiceImpl implements IUserFundService {

    @Resource
    private UserFundMapper userFundMapper;

    @Override
    public void add(UserFund userFund) {
        User loginInfo = SessionUtil.getLoginInfo();
        userFund.setUserId(loginInfo.getId());
        userFundMapper.insert(userFund);
    }
}

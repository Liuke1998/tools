package com.example.tools.service.impl;

import com.example.tools.service.IFundService;
import com.example.tools.service.IUserFundService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class FundServiceImpl implements IFundService {

    @Resource
    private IUserFundService userFundService;


}

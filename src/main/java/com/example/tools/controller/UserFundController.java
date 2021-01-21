package com.example.tools.controller;

import com.example.tools.entry.UserFund;
import com.example.tools.exception.BusinessException;
import com.example.tools.response.RestResponse;
import com.example.tools.service.IUserFundService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/userFund")
public class UserFundController {

    @Resource
    private IUserFundService userFundService;

    /**
     * 添加
     * @param userFund
     * @return
     */
    @PostMapping("/add")
    public RestResponse add(@RequestBody UserFund userFund){
        try {
            userFundService.add(userFund);
            return RestResponse.createSuccessResult("添加成功");
        } catch (BusinessException e){
            return RestResponse.createResult(401, e.getMessage());
        }
    }

}

package com.example.tools.controller;

import com.alibaba.fastjson.JSON;
import com.example.tools.httpService.HttpService;
import com.example.tools.response.RestResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/fund")
public class FundController {

    @Resource
    private HttpService httpService;

    @GetMapping("/getFundByCode")
    public RestResponse getFundByCode(String code){
        long time = new Date().getTime();
        String url =  String.format("http://fundgz.1234567.com.cn/js/%s.js?rt=%s", code, time);
        String result = null;
        try {
            result = httpService.doGet(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (StringUtils.isNotBlank(result)){
            result = result.substring(8, result.length() - 2);
        }
        Object parse = JSON.parse(result);
        return RestResponse.createSuccessResult(parse);
    }
}

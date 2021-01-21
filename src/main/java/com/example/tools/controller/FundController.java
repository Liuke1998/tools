package com.example.tools.controller;

import com.example.tools.httpService.HttpService;
import com.example.tools.response.RestResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/fund")
public class FundController {

    @Resource
    private HttpService httpService;

    @GetMapping("/getFundByCode")
    public RestResponse getFundByCode(String code){
        String url =  String.format("http://fundgz.1234567.com.cn/js/%s.js?rt=1589463125600", code);
        String result = null;
        try {
            result = httpService.doGet(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RestResponse.createSuccessResult(result);
    }
}

package com.example.tools.interceptor;


import com.example.tools.entry.User;
import com.example.tools.util.SessionUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String uri = request.getRequestURI();
        if (StringUtils.contains(uri, "/user/register") || StringUtils.contains(uri, "/user/login")){
            return true;
        }
        //判断有没有登录
        User loginInfo = SessionUtil.getLoginInfo();
        if (loginInfo == null){
            return false;
        }
        return true;
    }
}

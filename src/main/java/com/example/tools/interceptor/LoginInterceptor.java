package com.example.tools.interceptor;


import com.example.tools.anno.NotNeedLogin;
import com.example.tools.entry.User;
import com.example.tools.util.SessionUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //判断当前资源是不是方法对象,是则进行拦截校验
        if (handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            //如果有NeedLogin注解,并且没有登录,则跳转登录页面
            if (!handlerMethod.hasMethodAnnotation(NotNeedLogin.class) && SessionUtil.getLoginInfo() == null){
                //响应数据
                return false;
            }
        }
        return true;
    }
}

package com.example.tools.util;

import com.example.tools.entry.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 获取session中的对象
 */
public class SessionUtil {

    public static final String LOGININFO_IN_SESSION = "loginInfo_in_session";

    private SessionUtil(){}

    //获取request
    public static HttpServletRequest getRequest(){
        return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
    }
    //获取session
    public static HttpSession getSession(){
        return getRequest().getSession();
    }
    //保存用户信息到session中
    public static void setLoginInfo(User loginInfo){
        getSession().setAttribute(LOGININFO_IN_SESSION,loginInfo);
    }
    //取出session中的用户信息
    public static User getLoginInfo(){
        return (User) getSession().getAttribute(LOGININFO_IN_SESSION);
    }

}

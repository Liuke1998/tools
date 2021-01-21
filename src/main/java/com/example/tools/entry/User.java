package com.example.tools.entry;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * user
 * @author 
 */
@Data
public class User implements Serializable {

    private Integer id;
    //用户名
    private String username;
    //密码
    private String password;
    //创建时间
    private Date createDate;
    //最后登录时间
    private Date lastLoginDate;

    private static final long serialVersionUID = 1L;
}
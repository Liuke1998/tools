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

    private String username;

    private String password;

    private Date createDate;

    private static final long serialVersionUID = 1L;
}
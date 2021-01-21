package com.example.tools.entry;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class UserFund implements Serializable {

    private Integer id;
    //基金名
    private String name;
    //基金编码
    private String code;
    //持仓数
    private BigDecimal num;
    //用户id
    private Integer userId;
    //添加时间
    private Date createDate;

    private static final long serialVersionUID = 1L;
}

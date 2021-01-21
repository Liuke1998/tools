package com.example.tools.response;

import java.io.Serializable;

/**
 ** 异常枚举接口
 */
public interface ExceptionEnum extends Serializable {

    /**
     ** 获取异常编码
     */
    Integer getCode();

    /**
     ** 获取异常信息
     */
    String getMessage();
}

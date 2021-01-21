package com.example.tools.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse {
    /**
     * 响应码
     */
    private int code;
    /**
     * 响应消息
     */
    private String msg;

    protected BaseResponse() {}

    protected BaseResponse(CodeEnum code) {
        this.code = code.getCode();
        this.msg = code.getMsg();
    }

    public static BaseResponse out(CodeEnum code) {
        return new BaseResponse(code);
    }
}

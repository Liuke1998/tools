package com.example.tools.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

/**
 * REST返回结果类
 */
@Getter
@Setter
public class RestResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String OK = "OK";
    private static final String ERROR = "Error";

    private int code;

    private long timestamp;

    private String msg;
    /**
     * 状态200成功，10000失败
     */
    private String status;

    private T data;

    public RestResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.timestamp = Instant.now().toEpochMilli();
    }

    public RestResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.timestamp = Instant.now().toEpochMilli();
    }

    public RestResponse(int code, String msg, String status, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.status = status;
        this.timestamp = Instant.now().toEpochMilli();
    }

    public RestResponse() {}

    public static <T> RestResponse<T> createDefaultErrorResult() {
        return new RestResponse<>(new ErrorTip().getCode(), ERROR);
    }

    public static <T> RestResponse<T> createErrorResult(T data) {
        return new RestResponse<>(new ErrorTip().getCode(), ERROR, data);
    }

    public static <T> RestResponse<T> createErrorResult(T data, String msg) {
        return new RestResponse<>(new ErrorTip().getCode(), msg, ERROR, data);
    }

    public static <T> RestResponse<T> createSuccessResult(T data, String msg) {
        return new RestResponse<>(new SuccessTip().getCode(), msg, OK, data);
    }

    public static <T> RestResponse<T> createSuccessResult(T data) {
        return new RestResponse<>(new SuccessTip().getCode(), new SuccessTip().getMessage(), OK, data);
    }

    public static <T> RestResponse<T> createSuccessResult() {
        return new RestResponse<>(new SuccessTip().getCode(), new SuccessTip().getMessage());
    }

    public static <T> RestResponse<T> createDefaultSuccResult() {
        return new RestResponse<>(new SuccessTip().getCode(), OK);
    }

    public static <T> RestResponse<T> createResult(int code, String msg, T data) {
        return new RestResponse<>(code, msg, data);
    }

    public static <T> RestResponse<T> createResult(ExceptionEnum exceptionEnum, T data) {
        return new RestResponse<>(exceptionEnum.getCode(), exceptionEnum.getMessage(), data);
    }

    public static <T> RestResponse<T> createResult(ExceptionEnum exceptionEnum) {
        return new RestResponse<>(exceptionEnum.getCode(), exceptionEnum.getMessage());
    }

    public static <T> RestResponse<T> createResult(int code, String msg) {
        return new RestResponse<>(code, msg);
    }

    public void setData(T data) {
        if (data != null) {
            this.status = "200";
        } else {
            this.status = "10000";
        }
        this.data = data;
    }
}
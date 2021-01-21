package com.example.tools.response;

public class ErrorTip extends Tip {

    public ErrorTip(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public ErrorTip() {
        super.code = 10000;
        super.message = "失败";
    }
}
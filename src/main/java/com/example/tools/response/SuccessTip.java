package com.example.tools.response;

public class SuccessTip extends Tip {

    public SuccessTip() {
        super.code = 200;
        super.message = "操作成功";
    }
}

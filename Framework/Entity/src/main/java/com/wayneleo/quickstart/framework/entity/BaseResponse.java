package com.wayneleo.quickstart.framework.entity;

import com.wayneleo.quickstart.framework.common.constant.BaseCode;

public class BaseResponse {
    private int code = BaseCode.FAIL.code;

    public int getCode() {
        return code;
    }

    public void setCode( int Code ) {
        this.code = Code;
    }
}

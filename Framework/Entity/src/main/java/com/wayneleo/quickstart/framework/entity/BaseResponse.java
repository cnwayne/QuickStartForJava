package com.wayneleo.quickstart.framework.entity;

import com.wayneleo.quickstart.framework.common.constant.BaseCode;

public class BaseResponse {
    private int    code = BaseCode.SUCCESS.code;
    private String msg  = BaseCode.SUCCESS.msg;

    public int getCode() {
        return code;
    }

    public void setCode( int Code ) {
        this.code = Code;
    }

    public String getMsg() {
        this.msg = null == msg ? "" : msg;
        return msg;
    }

    public void setMsg( String Msg ) {
        this.msg = Msg;
    }
}

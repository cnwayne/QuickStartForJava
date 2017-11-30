package com.wayneleo.quickstart.framework.base.exception;

import java.io.Serializable;

public class BaseResponse implements Serializable {
    private static final long serialVersionUID = 6814411065174535359L;
    private int               code             = BaseCode.FAILED;

    public int getCode() {
        return code;
    }

    public void setCode( int Code ) {
        this.code = Code;
    }
}

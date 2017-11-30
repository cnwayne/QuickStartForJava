package com.wayneleo.quickstart.framework.core.error;

import com.wayneleo.quickstart.framework.base.exception.BaseCode;
import com.wayneleo.quickstart.framework.base.exception.BaseResponse;

public class ErrorVO extends BaseResponse {
    private static final long serialVersionUID = 8861782797685704716L;

    public ErrorVO( int code ) {
        super();
        if ( 0 == code ) {
            code = BaseCode.FAILED;
        }
        this.setCode( code );
    }
}

package com.wayneleo.quickstart.framework.core.error;

import com.wayneleo.quickstart.framework.entity.BaseResponse;

public class ErrorVO extends BaseResponse {
    public ErrorVO( int code ) {
        super();
        this.setCode( code );
    }
}

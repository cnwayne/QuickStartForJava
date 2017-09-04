package com.wayneleo.quickstart.framework.core.error;

import com.wayneleo.quickstart.framework.common.constant.BaseCode;
import com.wayneleo.quickstart.framework.entity.BaseResponse;

public class ErrorVO extends BaseResponse {
    /**
     * 不提供编码，默认为BaseCode.FAIL.code的值
     * 
     * @param msg 错误信息
     */
    public ErrorVO( String msg ) {
        super();
        this.setCode( BaseCode.FAIL.code );
        this.setMsg( msg );
    }

    public ErrorVO( int code, String msg ) {
        super();
        this.setCode( code );
        this.setMsg( msg );
    }
}

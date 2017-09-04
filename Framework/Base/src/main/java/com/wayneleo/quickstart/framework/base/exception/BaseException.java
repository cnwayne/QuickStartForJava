package com.wayneleo.quickstart.framework.base.exception;

import com.wayneleo.quickstart.framework.common.constant.BaseCode;

public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 7351576346012891586L;
    private int               code             = BaseCode.FAIL.code;

    public BaseException( String msg ) {
        super( msg );
    }

    public BaseException( String msg, int code ) {
        super( msg );
        this.code = code;
    }

    public BaseException( String msg, Throwable e ) {
        super( msg, e );
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "-" + code;
    }
}

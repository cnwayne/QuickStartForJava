package com.wayneleo.quickstart.framework.base;

public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 7351576346012891586L;
    private int               code;

    public BaseException( int code, String msg ) {
        super( msg );
        this.code = code;
    }

    public BaseException( int code, Throwable e ) {
        super( e );
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "-" + code;
    }
}

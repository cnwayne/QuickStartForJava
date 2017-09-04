package com.wayneleo.quickstart.framework.common.constant;

public enum BaseCode {
    SUCCESS( 0, "" ) // 成功
    , FAIL( -9999, "" ) // 失败
    ;
    /**************************************
     ************ 在上面列出编码 *************
     **************************************/
    public Integer code;
    public String  msg;

    private BaseCode( int code, String msg ) {
        this.code = code;
        this.msg = msg;
    }
}

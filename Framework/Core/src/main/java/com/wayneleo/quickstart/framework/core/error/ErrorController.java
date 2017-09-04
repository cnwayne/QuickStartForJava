package com.wayneleo.quickstart.framework.core.error;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wayneleo.quickstart.framework.base.exception.BaseException;
import com.wayneleo.quickstart.framework.common.constant.BaseCode;
import com.wayneleo.quickstart.framework.common.constant.Constant;

@RestController
public class ErrorController implements org.springframework.boot.autoconfigure.web.ErrorController {
    @RequestMapping( value = "/error" )
    public Object handleError( HttpServletRequest req, HttpServletResponse resp ) {
        Object exception = req.getAttribute( Constant.EXCEPTION_KEY );
        if ( null == exception ) {
            exception = new Exception( "SERVER ERROR !" );
        }
        Exception e = ( Exception ) exception;
        int errorCode = BaseCode.FAIL.code;
        if ( e instanceof BaseException ) {
            errorCode = ( ( BaseException ) e ).getCode();
        }
        resp.setStatus( 400 );
        return new ErrorVO( errorCode, e.getMessage() );
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}

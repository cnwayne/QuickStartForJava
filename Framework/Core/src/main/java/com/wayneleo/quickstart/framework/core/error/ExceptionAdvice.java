package com.wayneleo.quickstart.framework.core.error;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.wayneleo.quickstart.framework.base.exception.BaseException;
import com.wayneleo.quickstart.framework.common.constant.Constant;

@ControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler( value = Throwable.class )
    public void handleException( HttpServletRequest req, Throwable exception ) throws Throwable {
        if ( exception instanceof BaseException ) {
            req.setAttribute( Constant.EXCEPTION_KEY, exception );
        }
        throw exception;
    }
}

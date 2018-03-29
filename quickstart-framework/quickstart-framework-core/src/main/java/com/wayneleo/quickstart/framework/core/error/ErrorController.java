package com.wayneleo.quickstart.framework.core.error;

import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ErrorProperties.IncludeStacktrace;
import org.springframework.boot.autoconfigure.web.ErrorViewResolver;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.wayneleo.quickstart.framework.base.BaseException;

@RequestMapping( "${server.error.path:${error.path:/error}}" )
public class ErrorController extends AbstractErrorController {
    private static final Logger   LOG = LoggerFactory.getLogger( ErrorController.class );
    private final ErrorProperties errorProperties;

    /**
     * Create a new {@link BasicErrorController} instance.
     * 
     * @param errorAttributes the error attributes
     * @param errorProperties configuration properties
     */
    public ErrorController( ErrorAttributes errorAttributes, ErrorProperties errorProperties ) {
        this( errorAttributes, errorProperties, Collections.<ErrorViewResolver> emptyList() );
    }

    /**
     * Create a new {@link BasicErrorController} instance.
     * 
     * @param errorAttributes the error attributes
     * @param errorProperties configuration properties
     * @param errorViewResolvers error view resolvers
     */
    public ErrorController(
            ErrorAttributes errorAttributes,
            ErrorProperties errorProperties,
            List<ErrorViewResolver> errorViewResolvers ) {
        super( errorAttributes, errorViewResolvers );
        Assert.notNull( errorProperties, "ErrorProperties must not be null" );
        this.errorProperties = errorProperties;
    }

    @Override
    public String getErrorPath() {
        return this.errorProperties.getPath();
    }

    @RequestMapping
    @ResponseBody
    public ErrorVO error( HttpServletRequest request ) {
        RequestAttributes requestAttributes = new ServletRequestAttributes( request );
        Integer status = null;
        Throwable error = getException( requestAttributes );
        if ( ( null != error ) && ( error instanceof BaseException ) ) {
            LOG.error( "An exception occurred ", error );
            status = ( ( BaseException ) error ).getCode();
        }
        else if ( null != error ) {
            LOG.error( "An exception occurred ", error );
            status = 400;
        }
        else {
            status = getAttribute( requestAttributes, "javax.servlet.error.status_code" );
            status = null == status ? 999 : status;
        }
        return new ErrorVO( status );
    }

    public Throwable getException( RequestAttributes requestAttributes ) {
        Throwable exception = getAttribute( requestAttributes, DefaultErrorAttributes.class.getName() + ".ERROR" );
        if ( exception == null ) {
            exception = getAttribute( requestAttributes, "javax.servlet.error.exception" );
        }
        return exception;
    }

    @SuppressWarnings( "unchecked" )
    private <T> T getAttribute( RequestAttributes requestAttributes, String name ) {
        return ( T ) requestAttributes.getAttribute( name, RequestAttributes.SCOPE_REQUEST );
    }

    /**
     * Determine if the stacktrace attribute should be included.
     * 
     * @param request the source request
     * @param produces the media type produced (or {@code MediaType.ALL})
     * @return if the stacktrace attribute should be included
     */
    protected boolean isIncludeStackTrace( HttpServletRequest request, MediaType produces ) {
        IncludeStacktrace include = getErrorProperties().getIncludeStacktrace();
        if ( include == IncludeStacktrace.ALWAYS ) {
            return true;
        }
        if ( include == IncludeStacktrace.ON_TRACE_PARAM ) {
            return getTraceParameter( request );
        }
        return false;
    }

    /**
     * Provide access to the error properties.
     * 
     * @return the error properties
     */
    protected ErrorProperties getErrorProperties() {
        return this.errorProperties;
    }
}

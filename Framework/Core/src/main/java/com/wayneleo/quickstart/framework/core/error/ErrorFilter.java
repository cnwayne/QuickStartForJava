package com.wayneleo.quickstart.framework.core.error;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import com.wayneleo.quickstart.framework.common.constant.BaseCode;

public class ErrorFilter implements Filter {
    @Override
    public void destroy() {}

    @Override
    public void doFilter( ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain )
            throws IOException, ServletException {
        try {
            chain.doFilter( servletRequest, servletResponse );
        }
        catch ( Exception e ) {
            servletResponse.getWriter().print(
                    "{\"resultCode\":\"" + BaseCode.FAIL.code + "\",\"resultMsg\":\"" + BaseCode.FAIL.msg + "\"}" );;
        }
    }

    @Override
    public void init( FilterConfig config ) throws ServletException {}
}

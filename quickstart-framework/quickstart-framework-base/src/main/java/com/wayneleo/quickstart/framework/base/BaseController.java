package com.wayneleo.quickstart.framework.base;

import javax.servlet.http.HttpServletRequest;

public abstract class BaseController {
    /**
     * 获取被访问路径
     * 
     * @param request HttpServletRequest对象
     * @return URL字符串
     */
    protected final String getURL( HttpServletRequest request ) {
        StringBuilder url = new StringBuilder();
        try {
            url.append( request.getScheme() );
            url.append( "://" );
            url.append( request.getServerName() );
            url.append( ":" );
            url.append( request.getServerPort() );
            url.append( request.getContextPath() );
            url.append( "/" );
        }
        catch ( Exception ex ) {
            ex.printStackTrace();
        }
        return url.toString();
    }
}

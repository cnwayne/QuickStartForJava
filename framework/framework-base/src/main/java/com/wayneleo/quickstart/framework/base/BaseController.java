package com.wayneleo.quickstart.framework.base;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <pre>
 * 基础Controller类型
 * 实现部分公用方法
 * </pre>
 *
 * @author wayne
 */
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

    /**
     * 关闭窗口
     */
    protected void closeWindow( HttpServletResponse response ) {
        StringBuffer html = new StringBuffer();
        html.append( "<script type=\"text/javascript\">" );
        html.append( "window.opener=null;" );
        html.append( "window.open('', '_self', '');" );
        html.append( "window.close();" );
        html.append( "</script>" );
        this.renderHtml( html.toString(), response );
    }

    /**
     * 直接输出字符串.
     */
    protected String renderText( String text, HttpServletResponse response ) {
        return render( text, "text/plain;charset=UTF-8", response );
    }

    /**
     * 直接输出HTML.
     */
    protected String renderHtml( String html, HttpServletResponse response ) {
        return render( html, "text/html;charset=UTF-8", response );
    }

    /**
     * 直接输出XML.
     */
    protected String renderXML( String xml, HttpServletResponse response ) {
        return render( xml, "text/xml;charset=UTF-8", response );
    }

    /**
     * 直接输出文本.
     */
    protected String render( String text, String contentType, HttpServletResponse response ) {
        try {
            response.setContentType( contentType );
            response.getWriter().write( text );
        }
        catch ( IOException e ) {
            throw new BaseException( 10001, e );
        }
        return null;
    }
}

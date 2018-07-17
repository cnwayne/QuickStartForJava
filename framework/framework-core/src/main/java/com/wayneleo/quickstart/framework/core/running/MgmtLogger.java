package com.wayneleo.quickstart.framework.core.running;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import com.wayneleo.quickstart.framework.base.BaseException;
import com.wayneleo.quickstart.framework.base.BaseResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController( "MGMT_LOGGER" )
public class MgmtLogger {
    @Autowired
    private MgmtConfig mgmtConfig;

    @RequestMapping( path = "/server/logger", method = RequestMethod.POST )
    public BaseResponse change( String secret, String flag, String name ) {
        BaseResponse response = new BaseResponse();
        response.setCode( 0 );
        if ( StringUtils.isNotEmpty( mgmtConfig.getSecret() ) ) {
            if ( !mgmtConfig.getSecret().equals( secret ) ) {
                throw new BaseException( 999, "Someone wants to change the logger level, but he didn't know the secret key. The secret key he's currently trying is :" + secret );
            }
        }
        if ( StringUtils.isEmpty( flag ) ) {
            throw new BaseException( 999, "The level flag can not be empty" );
        }
        if ( StringUtils.isEmpty( name ) ) {
            name = "root";
        }
        LoggerContext loggerContext = ( LoggerContext ) LoggerFactory.getILoggerFactory();
        loggerContext.getLogger( name ).setLevel( Level.toLevel( flag ) );
        return response;
    }
}

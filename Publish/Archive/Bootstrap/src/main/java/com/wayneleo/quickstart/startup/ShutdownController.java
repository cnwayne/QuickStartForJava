package com.wayneleo.quickstart.startup;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.wayneleo.quickstart.framework.base.exception.BaseException;
import com.wayneleo.quickstart.framework.entity.BaseResponse;

@RestController( "MGMT_SHUTDOWN" )
@ConfigurationProperties( prefix = "shutdown" )
public class ShutdownController {
    @Autowired
    private ApplicationContext applicationContext;
    private String             secret;

    public String getSecret() {
        return secret;
    }

    public void setSecret( String secret ) {
        this.secret = secret;
    }

    @RequestMapping( path = "/server/shutdown", method = RequestMethod.POST )
    public BaseResponse doShutdown( String secret ) {
        final BaseResponse response = new BaseResponse();
        response.setCode( 0 );
        if ( StringUtils.isNotEmpty( this.secret ) ) {
            if ( !this.secret.equals( secret ) ) {
                throw new BaseException(
                        "Someone wants to shutdown this server, but he doesn't know the secret. This time he tried the secret is :" +
                                secret,
                        999 );
            }
        }
        Thread thread = new Thread( new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep( 2000 );
                }
                catch ( InterruptedException e ) {
                    e.printStackTrace();
                }
                SpringApplication.exit( applicationContext, new ExitCodeGenerator() {
                    @Override
                    public int getExitCode() {
                        return response.getCode();
                    }
                } );
            }
        } );
        thread.start();
        return response;
    }
}

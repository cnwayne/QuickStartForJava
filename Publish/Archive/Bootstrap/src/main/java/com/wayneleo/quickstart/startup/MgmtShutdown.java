package com.wayneleo.quickstart.startup;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.wayneleo.quickstart.framework.base.exception.BaseException;
import com.wayneleo.quickstart.framework.base.exception.BaseResponse;
import com.wayneleo.quickstart.framework.core.running.MgmtConfig;

@RestController( "MGMT_SHUTDOWN" )
public class MgmtShutdown {
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private MgmtConfig         mgmtConfig;

    @RequestMapping( path = "/server/shutdown", method = RequestMethod.POST )
    public BaseResponse doShutdown( String secret ) {
        final BaseResponse response = new BaseResponse();
        response.setCode( 0 );
        if ( StringUtils.isNotEmpty( mgmtConfig.getSecret() ) ) {
            if ( !mgmtConfig.getSecret().equals( secret ) ) {
                throw new BaseException(
                        999,
                        "Someone wants to shutdown this server, but he doesn't know the secret. This time he tried the secret is :" +
                                secret );
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

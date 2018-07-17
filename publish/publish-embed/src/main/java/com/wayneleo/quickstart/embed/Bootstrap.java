package com.wayneleo.quickstart.embed;

import com.wayneleo.quickstart.framework.core.conf.Application;
import org.springframework.boot.SpringApplication;

public class Bootstrap {
    public static void main( String[] args ) throws Exception {
        SpringApplication app = new SpringApplication( Application.class );
        app.run( args );
    }
}

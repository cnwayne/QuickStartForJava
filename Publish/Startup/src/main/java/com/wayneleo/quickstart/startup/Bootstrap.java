package com.wayneleo.quickstart.startup;

import org.springframework.boot.SpringApplication;
import com.wayneleo.quickstart.framework.core.conf.Application;

public class Bootstrap {
    public static void main( String[] args ) throws Exception {
        SpringApplication app = new SpringApplication( Application.class );
        app.run( args );
    }
}

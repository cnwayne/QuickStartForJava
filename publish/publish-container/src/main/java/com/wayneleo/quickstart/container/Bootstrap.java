package com.wayneleo.quickstart.container;

import com.wayneleo.quickstart.framework.core.conf.Application;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

public class Bootstrap extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure( SpringApplicationBuilder builder ) {
        builder.sources( Application.class );
        return super.configure( builder );
    }
}

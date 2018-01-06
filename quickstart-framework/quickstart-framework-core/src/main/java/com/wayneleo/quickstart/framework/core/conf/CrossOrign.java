package com.wayneleo.quickstart.framework.core.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class CrossOrign extends WebMvcConfigurerAdapter {
    @Override
    public void addCorsMappings( CorsRegistry registry ) {
        registry.addMapping( "/**" );
    }
}

package com.wayneleo.quickstart.framework.core.error;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ErrorConfiguration {
    @Bean
    public FilterRegistrationBean errorFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter( new ErrorFilter() );
        registration.addUrlPatterns( "*" );
        registration.setName( "errorFilter" );
        registration.setOrder( 1 );
        return registration;
    }
}

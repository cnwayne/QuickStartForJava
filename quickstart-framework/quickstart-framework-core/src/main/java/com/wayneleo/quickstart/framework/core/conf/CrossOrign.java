package com.wayneleo.quickstart.framework.core.conf;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ConfigurationProperties( "server.cors" )
public class CrossOrign extends WebMvcConfigurerAdapter {
    private String url;

    public void setUrl( String url ) {
        this.url = url;
    }

    @Override
    public void addCorsMappings( CorsRegistry registry ) {
        url = StringUtils.isEmpty( url ) ? "*" : url;
        registry.addMapping( "/**" )
                .allowedOrigins( url )
                .allowedMethods( "GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS" )
                .allowCredentials( true )
                .maxAge( 3600 );
    }
}

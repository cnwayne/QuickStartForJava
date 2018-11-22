package com.wayneleo.quickstart.framework.core.error;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorViewResolver;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ErrorConfiguration {
    private final ServerProperties  serverProperties;
    @Autowired( required = false )
    private List<ErrorViewResolver> errorViewResolvers;

    public ErrorConfiguration( ServerProperties serverProperties ) {
        this.serverProperties = serverProperties;
    }

    @Bean
    public ErrorController basicErrorController( ErrorAttributes errorAttributes ) {
        return new ErrorController( errorAttributes, this.serverProperties.getError(), this.errorViewResolvers );
    }
}

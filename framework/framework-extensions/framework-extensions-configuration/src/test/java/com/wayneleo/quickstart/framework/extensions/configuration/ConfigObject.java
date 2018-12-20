package com.wayneleo.quickstart.framework.extensions.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties( "test" )
public class ConfigObject {
    private String name;

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }
}

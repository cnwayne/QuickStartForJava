package com.wayneleo.quickstart.sample.shiro;

import java.util.HashMap;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties( "permissions" )
@SuppressWarnings( "serial" )
public class PermissionDefinition extends HashMap<String, String> {}

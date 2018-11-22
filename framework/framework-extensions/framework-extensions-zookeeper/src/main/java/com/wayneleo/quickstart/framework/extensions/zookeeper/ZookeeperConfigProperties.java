package com.wayneleo.quickstart.framework.extensions.zookeeper;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties( "extension.zookeeper" )
public class ZookeeperConfigProperties {
    private String  zkServers;
    private Integer sessionTimeoutMillis;

    public String getZkServers() {
        return zkServers;
    }

    public void setZkServers( String zkServers ) {
        this.zkServers = zkServers;
    }

    public Integer getSessionTimeoutMillis() {
        return sessionTimeoutMillis;
    }

    public void setSessionTimeoutMillis( Integer sessionTimeoutMillis ) {
        this.sessionTimeoutMillis = sessionTimeoutMillis;
    }
}

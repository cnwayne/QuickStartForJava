package com.wayneleo.quickstart.framework.extensions.redis;

import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties( prefix = "extension.redis" )
public class RedisClusterConfigProperties {
    private Integer      maxPoolCount;
    private Integer      maxRedirects;
    private Integer      timeout;
    private String       password;
    private List<String> nodes;

    public Integer getMaxPoolCount() {
        return maxPoolCount;
    }

    public void setMaxPoolCount( Integer maxPoolCount ) {
        this.maxPoolCount = maxPoolCount;
    }

    public Integer getMaxRedirects() {
        return maxRedirects;
    }

    public void setMaxRedirects( Integer maxRedirects ) {
        this.maxRedirects = maxRedirects;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout( Integer timeout ) {
        this.timeout = timeout;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public List<String> getNodes() {
        return nodes;
    }

    public void setNodes( List<String> nodes ) {
        this.nodes = nodes;
    }
}

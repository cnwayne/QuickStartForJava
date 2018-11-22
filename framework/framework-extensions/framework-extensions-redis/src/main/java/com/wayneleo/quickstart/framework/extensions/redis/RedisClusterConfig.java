package com.wayneleo.quickstart.framework.extensions.redis;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisClusterConfig {
    @Autowired
    private RedisClusterConfigProperties properties;

    @Bean
    public RedisConnectionFactory connectionFactory() {
        RedisClusterConfiguration clusterConfig = new RedisClusterConfiguration( properties.getNodes() );
        clusterConfig.setMaxRedirects( properties.getMaxRedirects() );
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal( properties.getMaxPoolCount() );
        JedisConnectionFactory factory = new JedisConnectionFactory( clusterConfig, poolConfig );
        factory.setTimeout( properties.getTimeout() );
        if ( StringUtils.isNotEmpty( properties.getPassword() ) ) {
            factory.setPassword( properties.getPassword() );
        }
        return factory;
    }
}

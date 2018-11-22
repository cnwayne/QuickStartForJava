package com.wayneleo.quickstart.framework.extensions.zookeeper;

import org.I0Itec.zkclient.IZkConnection;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZookeeperConfig {
    @Autowired
    private ZookeeperConfigProperties properties;

    @Bean
    public ZkClient zkClient() {
        IZkConnection connection = new ZkConnection( properties.getZkServers(), properties.getSessionTimeoutMillis() );
        ZkClient client = new ZkClient( connection );
        return client;
    }
}

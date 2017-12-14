package com.wayneleo.quickstart.framework.core.conf;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties( prefix = "threadPool" )
public class ThreadPoolConfig {
    private int corePoolSize;
    private int maxPoolSize;
    private int keepAliveTime;

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize( int corePoolSize ) {
        this.corePoolSize = corePoolSize;
    }

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize( int maxPoolSize ) {
        this.maxPoolSize = maxPoolSize;
    }

    public int getKeepAliveTime() {
        return keepAliveTime;
    }

    public void setKeepAliveTime( int keepAliveTime ) {
        this.keepAliveTime = keepAliveTime;
    }

    @Bean
    public Executor threadPool() {
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>( Integer.MAX_VALUE - 2 );
        return new ThreadPoolExecutor( corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.SECONDS, queue );
    }
}

package com.wayneleo.quickstart.framework.extensions.dubbo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.MethodConfig;
import com.alibaba.dubbo.config.ModuleConfig;
import com.alibaba.dubbo.config.MonitorConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;

@ConfigurationProperties( "extension.dubbo" )
public class DubboConfig {
    private ApplicationConfig application;
    private RegistryConfig    registry;
    private ProtocolConfig    protocol;
    private MonitorConfig     monitor;
    private ProviderConfig    provider;
    private ModuleConfig      module;
    private MethodConfig      method;
    private ConsumerConfig    consumer;

    public ApplicationConfig getApplication() {
        if ( null == application ) {
            application = new ApplicationConfig();
        }
        return application;
    }

    public void setApplication( ApplicationConfig application ) {
        this.application = application;
    }

    public RegistryConfig getRegistry() {
        if ( null == registry ) {
            registry = new RegistryConfig();
        }
        return registry;
    }

    public void setRegistry( RegistryConfig registry ) {
        this.registry = registry;
    }

    public ProtocolConfig getProtocol() {
        if ( null == protocol ) { 
            protocol = new ProtocolConfig();
        }
        return protocol;
    }

    public void setProtocol( ProtocolConfig protocol ) {
        this.protocol = protocol;
    }

    public MonitorConfig getMonitor() {
        if ( null == monitor ) {
            monitor = new MonitorConfig();
        }
        return monitor;
    }

    public void setMonitor( MonitorConfig monitor ) {
        this.monitor = monitor;
    }

    public ProviderConfig getProvider() {
        if ( null == provider ) {
            protocol = new ProtocolConfig();
        }
        return provider;
    }

    public void setProvider( ProviderConfig provider ) {
        this.provider = provider;
    }

    public ModuleConfig getModule() {
        if ( null == module ) {
            module = new ModuleConfig();
        }
        return module;
    }

    public void setModule( ModuleConfig module ) {
        this.module = module;
    }

    public MethodConfig getMethod() {
        if ( null == method ) {
            method = new MethodConfig();
        }
        return method;
    }

    public void setMethod( MethodConfig method ) {
        this.method = method;
    }

    public ConsumerConfig getConsumer() {
        if ( null == consumer ) {
            consumer = new ConsumerConfig();
        }
        return consumer;
    }

    public void setConsumer( ConsumerConfig consumer ) {
        this.consumer = consumer;
    }
}

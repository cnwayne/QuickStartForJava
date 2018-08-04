package com.wayneleo.quickstart.framework.extensions.dubbo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.MethodConfig;
import com.alibaba.dubbo.config.ModuleConfig;
import com.alibaba.dubbo.config.MonitorConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.wayneleo.quickstart.framework.core.conf.Application;
import com.wayneleo.quickstart.framework.core.integrate.ExtApplication;

@Configuration
@EnableConfigurationProperties( DubboConfig.class )
@DubboComponentScan( basePackages = {
        Application.FRAMEWORK_BASE_PACKAGE, ExtApplication.BASE_PACKAGE
} )
public class DubboApplication {
    @Autowired
    private DubboConfig config;

    @Bean
    public ApplicationConfig requestApplicationConfig() {
        ApplicationConfig applicationConfig = config.getApplication();
        if ( applicationConfig == null ) {
            applicationConfig = new ApplicationConfig();
        }
        return applicationConfig;
    }

    @Bean
    public RegistryConfig requestRegistryConfig() {
        RegistryConfig registryConfig = config.getRegistry();
        if ( registryConfig == null ) {
            registryConfig = new RegistryConfig();
        }
        return registryConfig;
    }

    @Bean
    public ProtocolConfig requestProtocolConfig() {
        ProtocolConfig protocolConfig = config.getProtocol();
        if ( protocolConfig == null ) {
            protocolConfig = new ProtocolConfig();
        }
        return protocolConfig;
    }

    @Bean
    public MonitorConfig requestMonitorConfig() {
        MonitorConfig monitorConfig = config.getMonitor();
        if ( monitorConfig == null ) {
            monitorConfig = new MonitorConfig();
        }
        return monitorConfig;
    }

    @Bean
    public ProviderConfig requestProviderConfig() {
        ProviderConfig providerConfig = config.getProvider();
        if ( providerConfig == null ) {
            providerConfig = new ProviderConfig();
        }
        return providerConfig;
    }

    @Bean
    public ModuleConfig requestModuleConfig() {
        return config.getModule();
    }

    @Bean
    public MethodConfig requestMethodConfig() {
        MethodConfig methodConfig = config.getMethod();
        if ( methodConfig == null ) {
            methodConfig = new MethodConfig();
        }
        return methodConfig;
    }

    @Bean
    public ConsumerConfig requestConsumerConfig() {
        ConsumerConfig consumerConfig = config.getConsumer();
        if ( consumerConfig == null ) {
            consumerConfig = new ConsumerConfig();
        }
        return consumerConfig;
    }
}

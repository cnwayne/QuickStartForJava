package com.wayneleo.quickstart.embed;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.AbstractProtocol;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeepAlive {
    @Bean
    public EmbeddedServletContainerFactory getEmbeddedServletContainerFactory() {
        TomcatEmbeddedServletContainerFactory containerFactory = new TomcatEmbeddedServletContainerFactory();
        containerFactory.addConnectorCustomizers( new TomcatConnectorCustomizer() {
            @Override
            public void customize( Connector connector ) {
                ( ( AbstractProtocol<?> ) connector.getProtocolHandler() ).setConnectionTimeout( 10000 );
            }
        } );
        return containerFactory;
    }
}

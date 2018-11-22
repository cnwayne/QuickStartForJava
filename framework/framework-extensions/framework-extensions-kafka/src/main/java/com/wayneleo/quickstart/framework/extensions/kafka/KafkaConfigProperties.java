package com.wayneleo.quickstart.framework.extensions.kafka;

import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.common.protocol.SecurityProtocol;
import org.apache.kafka.common.security.plain.PlainLoginModule;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.kafka.security.jaas.KafkaJaasLoginModuleInitializer.ControlFlag;
import org.springframework.stereotype.Component;
import com.wayneleo.quickstart.framework.base.BaseCode;
import com.wayneleo.quickstart.framework.base.BaseException;

@Component
@ConfigurationProperties( "extension.kafka" )
public class KafkaConfigProperties {
    private String   bootstrapServers;
    private Producer producer;
    private Consumer consumer;
    private Jaas     jaas;

    public static class Producer {
        private String keySerializer   = StringSerializer.class.getName();
        private String valueSerializer = StringSerializer.class.getName();

        @SuppressWarnings( "rawtypes" )
        public Class getKeySerializer() {
            try {
                return Class.forName( keySerializer );
            }
            catch ( ClassNotFoundException e ) {
                throw new BaseException( BaseCode.FAILED, e );
            }
        }

        public void setKeySerializer( String keySerializer ) {
            this.keySerializer = keySerializer;
        }

        @SuppressWarnings( "rawtypes" )
        public Class getValueSerializer() {
            try {
                return Class.forName( valueSerializer );
            }
            catch ( ClassNotFoundException e ) {
                throw new BaseException( BaseCode.FAILED, e );
            }
        }

        public void setValueSerializer( String valueSerializer ) {
            this.valueSerializer = valueSerializer;
        }
    }

    public static class Consumer {
        private String  keyDeserializer   = StringDeserializer.class.getName();
        private String  valueDeserializer = StringDeserializer.class.getName();
        private Integer concurrency;
        private Integer pollTimeout;
        private String  securityProtocol  = SecurityProtocol.SASL_PLAINTEXT.name;
        private String  saslMechanism     = "PLAIN";

        @SuppressWarnings( "rawtypes" )
        public Class getKeyDeserializer() {
            try {
                return Class.forName( keyDeserializer );
            }
            catch ( ClassNotFoundException e ) {
                throw new BaseException( BaseCode.FAILED, e );
            }
        }

        public void setKeyDeserializer( String keyDeserializer ) {
            this.keyDeserializer = keyDeserializer;
        }

        @SuppressWarnings( "rawtypes" )
        public Class getValueDeserializer() {
            try {
                return Class.forName( valueDeserializer );
            }
            catch ( ClassNotFoundException e ) {
                throw new BaseException( BaseCode.FAILED, e );
            }
        }

        public void setValueDeserializer( String valueDeserializer ) {
            this.valueDeserializer = valueDeserializer;
        }

        public Integer getConcurrency() {
            return concurrency;
        }

        public void setConcurrency( Integer concurrency ) {
            this.concurrency = concurrency;
        }

        public Integer getPollTimeout() {
            return pollTimeout;
        }

        public void setPollTimeout( Integer pollTimeout ) {
            this.pollTimeout = pollTimeout;
        }

        public String getSecurityProtocol() {
            return securityProtocol;
        }

        public void setSecurityProtocol( String securityProtocol ) {
            this.securityProtocol = securityProtocol;
        }

        public String getSaslMechanism() {
            return saslMechanism;
        }

        public void setSaslMechanism( String saslMechanism ) {
            this.saslMechanism = saslMechanism;
        }
    }

    public static class Jaas {
        private String      loginModule = PlainLoginModule.class.getName();
        private ControlFlag controlFlag = ControlFlag.REQUIRED;
        private String      username;
        private String      password;

        public boolean isEnabled() {
            if ( StringUtils.isNotEmpty( loginModule ) &&
                    StringUtils.isNotEmpty( username ) &&
                    StringUtils.isNotEmpty( password ) ) {
                return true;
            }
            return false;
        }

        public String getLoginModule() {
            return loginModule;
        }

        public void setLoginModule( String loginModule ) {
            this.loginModule = loginModule;
        }

        public ControlFlag getControlFlag() {
            return controlFlag;
        }

        public void setControlFlag( ControlFlag controlFlag ) {
            this.controlFlag = controlFlag;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername( String username ) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword( String password ) {
            this.password = password;
        }
    }

    public String getBootstrapServers() {
        return bootstrapServers;
    }

    public void setBootstrapServers( String bootstrapServers ) {
        this.bootstrapServers = bootstrapServers;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer( Producer producer ) {
        this.producer = producer;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer( Consumer consumer ) {
        this.consumer = consumer;
    }

    public Jaas getJaas() {
        return jaas;
    }

    public void setJaas( Jaas jaas ) {
        this.jaas = jaas;
    }

    public boolean useJaas() {
        if ( null != jaas && jaas.isEnabled() ) {
            return true;
        }
        return false;
    }
}

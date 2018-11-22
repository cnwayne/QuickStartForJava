package com.wayneleo.quickstart.framework.extensions.kafka;

import java.util.Map;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.security.plain.PlainLoginModule;
import org.springframework.kafka.security.jaas.KafkaJaasLoginModuleInitializer.ControlFlag;

public class JaasHelper {
    private static final StringBuilder SASL_JAAS_CONFIG_PATTERN = new StringBuilder();
    static {
        SASL_JAAS_CONFIG_PATTERN.append( PlainLoginModule.class.getName() );
        SASL_JAAS_CONFIG_PATTERN.append( " " );
        SASL_JAAS_CONFIG_PATTERN.append( ControlFlag.REQUIRED );
        SASL_JAAS_CONFIG_PATTERN.append( " username=\"%s\" password=\"%s\";" );
    }

    public static void ifExistsJass( KafkaConfigProperties conf, Map<String, Object> props ) {
        if ( !conf.useJaas() )
            return;
        props.put( CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, conf.getConsumer().getSecurityProtocol() );
        props.put( SaslConfigs.SASL_MECHANISM, conf.getConsumer().getSaslMechanism() );
        props.put(
                SaslConfigs.SASL_JAAS_CONFIG,
                String.format(
                        SASL_JAAS_CONFIG_PATTERN.toString(),
                        conf.getJaas().getUsername(),
                        conf.getJaas().getPassword() ) );
    }
}

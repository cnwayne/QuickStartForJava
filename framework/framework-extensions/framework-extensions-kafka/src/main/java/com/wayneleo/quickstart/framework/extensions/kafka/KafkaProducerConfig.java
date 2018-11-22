package com.wayneleo.quickstart.framework.extensions.kafka;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaProducerConfig {
    @Autowired
    private KafkaConfigProperties properties;

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<String, String>( producerFactory() );
    }

    private ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<String, String>( producerConfigs() );
    }

    private Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put( ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers() );
        props.put( ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, properties.getProducer().getKeySerializer() );
        props.put( ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, properties.getProducer().getKeySerializer() );
        JaasHelper.ifExistsJass( properties, props );
        return props;
    }
}

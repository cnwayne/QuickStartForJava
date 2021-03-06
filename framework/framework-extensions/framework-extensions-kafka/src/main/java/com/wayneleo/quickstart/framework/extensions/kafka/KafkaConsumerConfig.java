package com.wayneleo.quickstart.framework.extensions.kafka;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {
    @Autowired
    private KafkaConfigProperties properties;

    @Bean
    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<Integer, String>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory( consumerFactory() );
        factory.setConcurrency( properties.getConsumer().getConcurrency() );
        factory.getContainerProperties().setPollTimeout( properties.getConsumer().getPollTimeout() );
        return factory;
    }

    private ConsumerFactory<Integer, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>( consumerConfigs() );
    }

    private Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put( ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers() );
        props.put( ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, properties.getConsumer().getKeyDeserializer() );
        props.put( ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, properties.getConsumer().getValueDeserializer() );
        JaasHelper.ifExistsJass( properties, props );
        return props;
    }
}

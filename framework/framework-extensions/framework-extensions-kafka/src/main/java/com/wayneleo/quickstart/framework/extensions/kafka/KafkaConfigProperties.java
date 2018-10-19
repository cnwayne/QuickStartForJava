package com.wayneleo.quickstart.framework.extensions.kafka;

import com.wayneleo.quickstart.framework.base.BaseCode;
import com.wayneleo.quickstart.framework.base.BaseException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "extension.kafka")
public class KafkaConfigProperties {
    private String bootstrapServers;
    private Producer producer;
    private Consumer consumer;

    public static class Producer {
        private String keySerializer;
        private String valueSerializer;

        public Class getKeySerializer() {
            try {
                return Class.forName(keySerializer);
            } catch (ClassNotFoundException e) {
                throw new BaseException(BaseCode.FAILED, e);
            }
        }

        public void setKeySerializer(String keySerializer) {
            this.keySerializer = keySerializer;
        }

        public Class getValueSerializer() {
            try {
                return Class.forName(valueSerializer);
            } catch (ClassNotFoundException e) {
                throw new BaseException(BaseCode.FAILED, e);
            }
        }

        public void setValueSerializer(String valueSerializer) {
            this.valueSerializer = valueSerializer;
        }
    }

    public static class Consumer {
        private String keyDeserializer;
        private String valueDeserializer;
        private Integer concurrency;
        private Integer pollTimeout;

        public Class getKeyDeserializer() {
            try {
                return Class.forName(keyDeserializer);
            } catch (ClassNotFoundException e) {
                throw new BaseException(BaseCode.FAILED, e);
            }
        }

        public void setKeyDeserializer(String keyDeserializer) {
            this.keyDeserializer = keyDeserializer;
        }

        public Class getValueDeserializer() {
            try {
                return Class.forName(valueDeserializer);
            } catch (ClassNotFoundException e) {
                throw new BaseException(BaseCode.FAILED, e);
            }
        }

        public void setValueDeserializer(String valueDeserializer) {
            this.valueDeserializer = valueDeserializer;
        }

        public Integer getConcurrency() {
            return concurrency;
        }

        public void setConcurrency(Integer concurrency) {
            this.concurrency = concurrency;
        }

        public Integer getPollTimeout() {
            return pollTimeout;
        }

        public void setPollTimeout(Integer pollTimeout) {
            this.pollTimeout = pollTimeout;
        }
    }

    public String getBootstrapServers() {
        return bootstrapServers;
    }

    public void setBootstrapServers(String bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }
}

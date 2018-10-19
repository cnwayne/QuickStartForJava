package com.wayneleo.quickstart;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * 消费者例子
 * <p>
 * 1. 使用 @Component 保证类能被框架扫描到
 * 2. 使用 @KafkaListener 监听队列，用于消费
 */

@Component
public class ConsumerSample {
    @KafkaListener(topics = "test", groupId = "${extension.kafka.consumer.group-id}")
    public void receive(String msg) {
        System.out.println("receive message   : " + msg);
    }
}

package com.wayneleo.quickstart;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * 消费者例子
 * 
 * <p>
 * 1. 使用 <code>@Component</code> 保证类能被框架扫描到<br>
 * 2. 使用 <code>@KafkaListener</code> 监听队列，用于消费
 * </p>
 */
@Component
public class ConsumerSample {
    @KafkaListener( topics = "test", groupId = "sample" )
    public void receive( String msg ) {
        System.out.println( "receive message   : " + msg );
    }
}

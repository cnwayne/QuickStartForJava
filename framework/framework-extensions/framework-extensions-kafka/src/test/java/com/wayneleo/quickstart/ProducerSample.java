package com.wayneleo.quickstart;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import com.wayneleo.quickstart.publish.test.BaseTest;

/**
 * 生产者例子
 * <p>
 * 1. 注入 KafkaTemplate<String, String> 的实例<br>
 * 2. 使用 KafkaTemplate.send(...) 方法发送消息(类内有很多重载，自己看)
 */
public class ProducerSample extends BaseTest {
    static final String                   TOPIC_FOR_TEST = "test";
    static final Integer                  SENDING_TIMES  = 100;
    @Autowired
    private KafkaTemplate<String, String> template;

    @Override
    public void beforeTest() {}

    @Override
    public void afterTest() {}

    @Test
    public void send() throws Exception {
        for ( int i = 0; i < SENDING_TIMES; i++ ) {
            String msg = String.valueOf( System.currentTimeMillis() );
            this.template.send( TOPIC_FOR_TEST, msg ).get();
            System.out.println( String.format( "send message [%2d] : %s", i, msg ) );
            Thread.sleep( 100 );
        }
        Thread.sleep( 10000 );
    }
}

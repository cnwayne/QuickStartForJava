package com.wayneleo.quickstart;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisClusterConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import com.wayneleo.quickstart.publish.test.BaseTest;

public class Sample extends BaseTest {
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Override
    public void beforeTest() {}

    @Override
    public void afterTest() {}

    @Test
    public void hello() {
        System.out.println( "Hello World !" );
    }

    //@Test
    public void setting() {
        RedisClusterConnection connection = redisConnectionFactory.getClusterConnection();
        for ( int i = 0; i < 10; i++ ) {
            connection.set( ( "key_" + i ).getBytes(), ( "value_" + i ).getBytes() );
        }
        connection.close();
    }

    //@Test
    public void fetch() {
        RedisClusterConnection connection = redisConnectionFactory.getClusterConnection();
        for ( int i = 0; i < 10; i++ ) {
            connection.get( ( "key_" + i ).getBytes() );
        }
        connection.close();
    }
}

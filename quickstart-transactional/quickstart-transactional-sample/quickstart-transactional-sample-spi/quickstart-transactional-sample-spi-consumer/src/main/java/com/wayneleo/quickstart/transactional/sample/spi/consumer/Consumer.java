package com.wayneleo.quickstart.transactional.sample.spi.consumer;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.ServiceLoader;
import com.wayneleo.quickstart.transactional.sample.spi.api.Something;

public class Consumer {
    public static final int LOOPING_TIMES = 10000;

    public static void main( String[] args ) {
        for ( int i = 0; i < 10; i++ ) {
            loopInvoke();
        }
    }

    public static void loopInvoke() {
        long start = System.currentTimeMillis();
        for ( int i = 0; i < LOOPING_TIMES; i++ ) {
            newThing();
        }
        long total = System.currentTimeMillis() - start;
        System.out.println( String.format( "执行了 %d 次, 共用时 %d 毫秒, 平均每次用时 %s 毫秒 !", LOOPING_TIMES, total, BigDecimal.valueOf( total ).divide( BigDecimal.valueOf( LOOPING_TIMES ), 2, BigDecimal.ROUND_HALF_UP ) ) );
    }

    public static Something newThing() {
        ServiceLoader<Something> things = ServiceLoader.load( Something.class );
        Iterator<Something> iterator = things.iterator();
        if ( iterator.hasNext() ) return iterator.next();
        return null;
    }
}

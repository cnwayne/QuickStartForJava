package com.wayneleo.test;

import java.util.concurrent.atomic.AtomicInteger;

public class AAA {
    public static final int WAN_UNIT         = 10000;
    public static final int MAX_RUNNING_TIME = 30000;
    public static final int LOOPING_TIMES    = 1000 * WAN_UNIT;
    public static final int THREAD_NUM       = 1;

    public static void main( String[] args ) throws Exception {
        Counter counter = new Counter();
        Thread[] threads = new Thread[THREAD_NUM];
        for ( int i = 0; i < THREAD_NUM; i++ ) {
            threads[i] = new SampleThread( counter );
        }
        for ( int i = 0; i < THREAD_NUM; i++ ) {
            threads[i].start();
        }
        Long startDatetime = System.currentTimeMillis();
        Long endDatetime = startDatetime;
        while ( ( counter.getVal() < ( LOOPING_TIMES * THREAD_NUM ) ) &&
                ( ( ( endDatetime = System.currentTimeMillis() ) - startDatetime ) < MAX_RUNNING_TIME ) ) {
            Thread.sleep( 0 );
        }
        System.out.println(
                String.format( "计算结果是 : %d ;\n运行耗时是 : %d 毫秒。", counter.getVal(), ( endDatetime - startDatetime ) ) );
    }

    public static class SampleThread extends Thread {
        private static Counter counter;

        public SampleThread( Counter counter ) {
            SampleThread.counter = counter;
        }

        public static Integer getVal() {
            return counter.getVal();
        }

        @Override
        public void run() {
            try {
                for ( int i = 0; i < LOOPING_TIMES; i++ ) {
                    //Thread.sleep( 0 );
                    counter.increment();
                }
            }
            catch ( Exception e ) {
                e.printStackTrace();
            }
        }
    }

    public static class Counter {
        private SimpleLock lock = new SimpleLock();
        private Integer    val  = 0;

        public final void increment() {
            try {
                lock.lock();
                val++;
            }
            catch ( Exception e ) {
                e.printStackTrace();
                System.exit( -1 );
            }
            finally {
                lock.unlock();
            }
        }

        public Integer getVal() {
            return val;
        }
    }

    public static class SimpleLock {
        private static final Integer UNLOCK_SYNC_VAL = 0;
        private static final Integer LOCK_SYNC_VAL   = 1;
        private static AtomicInteger syncAtomic      = new AtomicInteger( UNLOCK_SYNC_VAL );

        public void lock() {
            try {
                while ( !syncAtomic.compareAndSet( UNLOCK_SYNC_VAL, LOCK_SYNC_VAL ) ) {
                    Thread.sleep( 0 );
                }
            }
            catch ( Exception e ) {
                e.printStackTrace();
            }
        }

        public void unlock() {
            syncAtomic.compareAndSet( LOCK_SYNC_VAL, UNLOCK_SYNC_VAL );
        }
    }
}

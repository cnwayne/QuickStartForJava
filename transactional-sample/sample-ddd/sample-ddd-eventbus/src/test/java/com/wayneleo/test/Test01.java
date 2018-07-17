package com.wayneleo.test;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

public class Test01 {
    public static void main( String[] args ) {
        EventBus bus = new EventBus();
        bus.register( new B() );
        A a = new A( bus );
        a.foo();
    }

    public static class A {
        EventBus bus;

        public void foo() {
            bus.post( "foo" );
        }

        public A( EventBus bus ) {
            this.bus = bus;
        }
    }

    public static class B {
        @Subscribe
        public void foo1( String c ) {
            System.out.println( "hello" + c );
        }

        @Subscribe
        public void foo2( String c ) {
            System.out.println( "hello" + c );
        }
    }
}

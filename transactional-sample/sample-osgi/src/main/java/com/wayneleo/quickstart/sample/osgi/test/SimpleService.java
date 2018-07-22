package com.wayneleo.quickstart.sample.osgi.test;

public class SimpleService implements IService {
    public final String datetime = String.valueOf( System.currentTimeMillis() );

    @Override
    public void doSomething() {
        System.out.println( "HelloWorld!" );
    }

    @Override
    public String getDatetime() {
        return datetime;
    }
}

package com.wayneleo.quickstart.publish.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wayneleo.quickstart.framework.core.conf.Application;

@RunWith( SpringInstanceTestClassRunner.class )
@SpringBootTest( classes = Application.class )
public abstract class BaseTest implements InstanceTestClassListener {
    @Autowired
    protected ObjectMapper mapper;

    protected void print2Console( String content ) {
        System.out.println( "\n================" );
        System.out.println( content );
        System.out.println( "================\n" );
    }

    protected void print4Json( Object result ) {
        String jsonStr = null;
        try {
            jsonStr = mapper.writeValueAsString( result );
        }
        catch ( Exception e ) {
            throw new RuntimeException( e );
        }
        System.out.println( "\n================" );
        System.out.println( jsonStr );
        System.out.println( "================\n" );
    }
}

package com.wayneleo.quickstart.transactional.sample.mvc;

import org.springframework.boot.CommandLineRunner;

public class Init implements CommandLineRunner {
    @Override
    public void run( String... args ) throws Exception {
        System.out.println( "Init.run()" );
    }
}

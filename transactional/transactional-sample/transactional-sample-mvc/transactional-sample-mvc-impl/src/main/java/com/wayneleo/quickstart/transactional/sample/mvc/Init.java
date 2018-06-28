package com.wayneleo.quickstart.transactional.sample.mvc;

import org.springframework.boot.CommandLineRunner;

/**
 * 初始化演示。在系统启动后开始执行"run"方法
 */
public class Init implements CommandLineRunner {
    @Override
    public void run( String... args ) throws Exception {
        System.out.println( "Init.run()" );
    }
}

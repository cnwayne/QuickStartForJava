package com.wayneleo.quickstart.transactional.sample.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * 初始化演示。在系统启动后开始执行"run"方法
 */
@Component
public class Init implements CommandLineRunner {
    @Autowired
    private SampleService service;

    @Override
    public void run( String... args ) throws Exception {
        System.out.println( "Init.run()" );
        Assert.notNull( service, SampleService.class.getSimpleName() + " 注入失败!" );
    }
}

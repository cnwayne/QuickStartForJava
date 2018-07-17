package com.wayneleo.quickstart.transactional.sample.mvc;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时器演示，需要在配置文件中加入"cache.duration"
 */
@Component
public class Schedule {
    @Scheduled( fixedDelayString = "${cache.duration}", initialDelay = 0 )
    public void doSchedule() {
        System.out.println( "Hello World !" );
    }
}

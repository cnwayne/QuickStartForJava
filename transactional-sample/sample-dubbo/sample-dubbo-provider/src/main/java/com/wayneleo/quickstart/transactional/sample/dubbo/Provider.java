package com.wayneleo.quickstart.transactional.sample.dubbo;

import com.alibaba.dubbo.config.annotation.Service;

@Service
public class Provider implements IService {
    @Override
    public TestResult sayHello( String name ) {
        return new TestResult();
    }
}

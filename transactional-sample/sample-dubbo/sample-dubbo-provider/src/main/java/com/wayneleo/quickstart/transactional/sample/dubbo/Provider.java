package com.wayneleo.quickstart.transactional.sample.dubbo;

import com.alibaba.dubbo.config.annotation.Service;

@Service
public class Provider implements IService {
    private static final long serialVersionUID = -8658899645754474922L;

    @Override
    public TestResult sayHello( String name ) {
        return new TestResult();
    }
}

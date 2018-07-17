package com.wayneleo.quickstart.transactional.sample.dubbo;

import com.alibaba.dubbo.config.annotation.Service;

@Service( version = "1.0.0" )
public class Provider implements IService {
    private static final long serialVersionUID = -8658899645754474922L;

    @Override
    public String sayHello( String name ) {
        return "Hello " + name + " !";
    }
}

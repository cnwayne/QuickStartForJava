package com.wayneleo.quickstart.transactional.sample.dubbo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;

@RestController( "W_Consumer" )
@RequestMapping( value = "/sample/dubbo/demo01" )
public class Consumer {
    @Reference
    private IService service;

    @RequestMapping( method = RequestMethod.GET )
    public Object sayHello( String name ) {
        return service.sayHello( name );
    }
}

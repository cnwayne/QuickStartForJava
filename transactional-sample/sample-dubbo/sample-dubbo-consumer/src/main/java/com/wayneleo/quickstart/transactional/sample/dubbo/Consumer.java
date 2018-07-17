package com.wayneleo.quickstart.transactional.sample.dubbo;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController( "W_Consumer" )
@RequestMapping( value = "/sample/dubbo/demo01" )
public class Consumer {
    @Reference( version = "1.0.0" )
    private IService service;

    @RequestMapping( method = RequestMethod.GET )
    public Object sayHello( String name ) {
        return service.sayHello( name );
    }
}

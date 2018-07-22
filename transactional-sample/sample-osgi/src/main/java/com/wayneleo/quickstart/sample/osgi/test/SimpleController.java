package com.wayneleo.quickstart.sample.osgi.test;

import com.wayneleo.quickstart.framework.base.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {
    @Autowired
    private IService service;

    @RequestMapping( path = "/test01" )
    public BaseResponse test01() {
        SimpleResp resp = new SimpleResp();
        resp.setDatetime( service.getDatetime() );
        return resp;
    }

    @RequestMapping( path = "/test02" )
    public BaseResponse test02() {
        SimpleResp resp = new SimpleResp();
        IService service = ServiceFactory.getService( "simpleService" );
        resp.setDatetime( service.getDatetime() );
        return resp;
    }
}

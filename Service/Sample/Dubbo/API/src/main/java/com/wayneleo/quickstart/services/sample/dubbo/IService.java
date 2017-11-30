package com.wayneleo.quickstart.services.sample.dubbo;

import com.wayneleo.quickstart.framework.base.exception.BaseInterface;

public interface IService extends BaseInterface {
    public String sayHello( String name );
}

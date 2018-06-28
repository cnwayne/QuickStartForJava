package com.wayneleo.quickstart.transactional.sample.dubbo;

import com.wayneleo.quickstart.framework.base.BaseInterface;

public interface IService extends BaseInterface {
    public String sayHello( String name );
}

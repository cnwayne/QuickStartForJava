package com.wayneleo.quickstart.services.sample.dubbo;

import java.io.Serializable;

public interface IService extends Serializable {
    public String sayHello( String name );
}

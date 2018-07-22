package com.wayneleo.quickstart.sample.osgi.test;

import org.springframework.beans.factory.FactoryBean;

public class ServiceFactoryBean implements FactoryBean<IService> {
    @Override
    public IService getObject() throws Exception {
        return new SimpleService();
    }

    @Override
    public Class<? extends IService> getObjectType() {
        return SimpleService.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}

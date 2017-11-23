package com.wayneleo.quickstart.framework.base.exception;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public abstract class StrategyFactory implements ApplicationContextAware {
    private static ApplicationContext context;

    @Override
    public void setApplicationContext( ApplicationContext applicationContext ) throws BeansException {
        context = applicationContext;
    }

    protected static final boolean containsBean( String beanName ) {
        return context.containsBean( beanName );
    }

    protected static final <T> T getBean( String beanName, Class<T> clazz ) {
        return getBean( beanName, clazz, true );
    }

    protected static final <T> T getBeanNotNull( String beanName, Class<T> clazz ) {
        return getBean( beanName, clazz, false );
    }

    private static final <T> T getBean( String beanName, Class<T> clazz, boolean nullable ) {
        if ( nullable && ( !containsBean( beanName ) ) ) {
            return null;
        }
        return context.getBean( beanName, clazz );
    }
}

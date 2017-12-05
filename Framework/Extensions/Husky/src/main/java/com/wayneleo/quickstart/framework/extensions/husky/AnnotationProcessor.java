package com.wayneleo.quickstart.framework.extensions.husky;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class AnnotationProcessor implements BeanFactoryPostProcessor, BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization( Object bean, String beanName ) throws BeansException {
        System.out.println( "\n\n==============" + bean.getClass().getName() + "\n\n");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization( Object bean, String beanName ) throws BeansException {
        return bean;
    }

    @Override
    public void postProcessBeanFactory( ConfigurableListableBeanFactory beanFactory ) throws BeansException {}
}

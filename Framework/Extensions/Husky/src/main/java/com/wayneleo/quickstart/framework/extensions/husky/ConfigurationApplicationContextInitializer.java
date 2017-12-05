package com.wayneleo.quickstart.framework.extensions.husky;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class ConfigurationApplicationContextInitializer implements
        ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize( ConfigurableApplicationContext applicationContext ) {
        AnnotationProcessor scanner = new AnnotationProcessor();
        applicationContext.addBeanFactoryPostProcessor( scanner );
        applicationContext.getBeanFactory().addBeanPostProcessor( scanner );
        applicationContext.getBeanFactory().registerSingleton( "annotationProcessor", scanner );
    }
}

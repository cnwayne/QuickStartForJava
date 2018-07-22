package com.wayneleo.quickstart.sample.osgi.test;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    //@Autowired
    //BeanNameGenerator beanNameGenerator;

    @Override
    public void postProcessBeanDefinitionRegistry( BeanDefinitionRegistry registry ) throws BeansException {
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass( ServiceFactoryBean.class );
        beanDefinition.setLazyInit( true );
        //String beanName = beanNameGenerator.generateBeanName( beanDefinition, registry );
        registry.registerBeanDefinition( "simpleService", beanDefinition );
    }

    @Override
    public void postProcessBeanFactory( ConfigurableListableBeanFactory beanFactory ) throws BeansException {}
}

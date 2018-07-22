package com.wayneleo.quickstart.sample.osgi.test;

import com.wayneleo.quickstart.framework.base.StrategyFactory;
import org.springframework.stereotype.Component;

@Component
public class ServiceFactory extends StrategyFactory {
    public static IService getService( String beanName ) {
        return getBean( beanName, IService.class );
    }
}

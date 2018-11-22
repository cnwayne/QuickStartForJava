package com.wayneleo.quickstart.sample.osgi.test;

import org.springframework.stereotype.Component;
import com.wayneleo.quickstart.framework.base.StrategyFactory;

@Component
public class ServiceFactory extends StrategyFactory {
    public static IService getService( String beanName ) {
        return getBean( beanName, IService.class );
    }
}

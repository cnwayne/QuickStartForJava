package com.wayneleo.quickstart.framework.core.thirdparty;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan( ExtApplication.BASE_PACKAGE )
@EnableJpaRepositories( ExtApplication.BASE_PACKAGE )
@ComponentScan( ExtApplication.BASE_PACKAGE )
public class ExtApplication {
    /** TODO 在这里修改需要Spring扫描的包名 ( modify the name of the package here ) */
    public static final String BASE_PACKAGE = "com.bridgeintelligent.test";
}

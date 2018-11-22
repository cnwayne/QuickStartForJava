package com.wayneleo.quickstart.framework.core.integrate;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.wayneleo.quickstart.framework.core.conf.Application;

/**
 * 框架扩展配置，为了不修改{@link Application}中的内容(因为任何人工操作都有可能出错)，可以在这里增加对其的扩展。
 */
@Configuration
@EntityScan( ExtApplication.BASE_PACKAGE )
@EnableJpaRepositories( ExtApplication.BASE_PACKAGE )
@ComponentScan( ExtApplication.BASE_PACKAGE )
public class ExtApplication {
    /**
     * TODO 在这里修改需要Spring扫描的包名 ( here to modify the name of the package that spring needs scan )
     */
    public static final String BASE_PACKAGE = "com.bridgeintelligent.test";
}

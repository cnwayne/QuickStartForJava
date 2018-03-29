package com.wayneleo.quickstart.framework.apidocs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class ApiDocs {
    @Bean
    public Docket Mobile() {
        return HttpApis.docs();
    }
}

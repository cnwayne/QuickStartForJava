package com.wayneleo.quickstart.framework.apidocs;

import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

public class HttpApis {
    public static Docket docs() {
        return new Docket( DocumentationType.SWAGGER_2 ).groupName( "HttpApis" )
                .genericModelSubstitutes( DeferredResult.class )
                .useDefaultResponseMessages( false )
                .forCodeGeneration( true )
                .pathMapping( "/" )
                .select()
                .apis( RequestHandlerSelectors.basePackage( "com.wayneleo.quickstart.services" ) )
                .paths( PathSelectors.regex( "/api/.*" ) )
                .build()
                .apiInfo( apiInfo() );
    }

    private static ApiInfo apiInfo() {
        return new ApiInfoBuilder().title( "客户画像系统接口说明" )
                .description( "本文档只说明HTTP接口" )
                .termsOfServiceUrl( "" )
                .version( "1.0.20170710" )
                .build();
    }
}

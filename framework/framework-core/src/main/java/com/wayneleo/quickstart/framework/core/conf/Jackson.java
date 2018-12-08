package com.wayneleo.quickstart.framework.core.conf;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
public class Jackson extends WebMvcConfigurerAdapter {
    private Jackson2ObjectMapperBuilder objectMapperBuilder;

    public Jackson() {
        objectMapperBuilder = objectMapperBuilder();
        objectMapperBuilder.featuresToDisable( SerializationFeature.FAIL_ON_EMPTY_BEANS );
    }

    @Override
    public void configureMessageConverters( List<HttpMessageConverter<?>> converters ) {
        converters.add( new MappingJackson2HttpMessageConverter( objectMapperBuilder.build() ) );
    }

    @Bean
    public ObjectMapper objectMapper() {
        return objectMapperBuilder.build();
    }

    private Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.serializationInclusion( JsonInclude.Include.NON_NULL );
        builder.failOnUnknownProperties( false );
        return builder;
    }
}

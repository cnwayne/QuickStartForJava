package com.wayneleo.quickstart.framework.core.conf;

import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.fasterxml.jackson.annotation.JsonInclude;

@Configuration
public class Jackson extends WebMvcConfigurerAdapter {
    @Override
    public void configureMessageConverters( List<HttpMessageConverter<?>> converters ) {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.serializationInclusion( JsonInclude.Include.NON_NULL );
        builder.failOnUnknownProperties( false );
        converters.add( new MappingJackson2HttpMessageConverter( builder.build() ) );
    }
}

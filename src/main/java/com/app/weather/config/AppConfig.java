package com.app.weather.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.app.weather.util.HttpClientUtil;

/**
* The spring application config class which 
* defines the beans and configurations to be 
* used by spring container.
*
* @author  Balasubramanian
* @version 1.0
* @since   2014-08-14 
*/
@Configuration
public class AppConfig {
    @Bean
    public HttpClientUtil httpClientUtil() {
        return new HttpClientUtil();
    }
    
    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
    
    @Bean
    public ContentNegotiatingViewResolver getContentNegotiatingViewResolver() {
    	ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
    	Map<String, String> mediaTypes = new HashMap<String, String>();
        mediaTypes.put("json", "application/x-json");
        resolver.setMediaTypes(mediaTypes);
    	return resolver;
    	
    }
}
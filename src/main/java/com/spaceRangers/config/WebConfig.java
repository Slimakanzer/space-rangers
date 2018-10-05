package com.spaceRangers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.XmlViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.spaceRangers.controller")
public class WebConfig {

    @Bean
    ViewResolver internalViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setOrder(0);
        resolver.setSuffix(".jsp");
        resolver.setPrefix("/WEB-INF/");
        return resolver;
    }

    @Bean
    ViewResolver xmlViewResolver(){
        XmlViewResolver resolver = new XmlViewResolver();
        resolver.setOrder(1);
        resolver.setLocation(new ClassPathResource("views.xml"));
        return resolver;
    }
}

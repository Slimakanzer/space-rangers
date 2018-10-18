package com.spaceRangers.config.web;

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
@ComponentScan({
        "com.spaceRangers.config.security",
        "com.spaceRangers.controller"
})
public class WebConfig {

    @Bean
    ViewResolver internalViewResolver(){
        System.out.println("Started view resolver");
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setOrder(0);
        resolver.setSuffix(".jsp");
        resolver.setPrefix("/WEB-INF/");
        return resolver;
    }

    ViewResolver xmlViewResolver(){
        XmlViewResolver resolver = new XmlViewResolver();
        resolver.setOrder(1);
        resolver.setLocation(new ClassPathResource("views.xml"));
        return resolver;
    }
}

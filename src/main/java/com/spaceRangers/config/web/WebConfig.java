package com.spaceRangers.config.web;

import com.spaceRangers.impl.MoneyEarner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.annotation.PostConstruct;

@Configuration
@EnableWebMvc
@ComponentScan({
        "com.spaceRangers.config.security",
        "com.spaceRangers.controller",
        "com.spaceRangers.config.database",
        "com.spaceRangers.config.websocket",
})
public class WebConfig extends WebMvcConfigurerAdapter {
    @Autowired
    MoneyEarner moneyEarner;

    @PostConstruct
    public void init(){
        moneyEarner.startEarnMoney();
    }

    @Bean
    ViewResolver internalViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setOrder(0);
        resolver.setSuffix(".jsp");
        resolver.setPrefix("/WEB-INF/");
        return resolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("static/**")
                .addResourceLocations("/WEB-INF/static/");

        registry.addResourceHandler("webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}

package com.spaceRangers.config.database;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("classpath:database.properties")
@ComponentScan("com.spaceRangers.config.database.persistence")
public class DatabaseConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer configurer() {
        System.out.println("Started postBeanFactory");

        return new PropertySourcesPlaceholderConfigurer();
    }
}

package com.spaceRangers.config.security.oauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("classpath:google.properties")
public class OauthConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer configurer() {

        return new PropertySourcesPlaceholderConfigurer();
    }
}
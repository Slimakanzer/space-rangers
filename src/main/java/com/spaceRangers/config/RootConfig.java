package com.spaceRangers.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan(basePackages = {
        "com.spaceRangers.config.database",
        "com.spaceRangers.config.security",
        "com.spaceRangers.config.web"
})
public class RootConfig extends WebMvcConfigurerAdapter {
}

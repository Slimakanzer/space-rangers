package com.spaceRangers.config.startup;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "com.spaceRangers.config.web"
})
public class RootConfig{
}

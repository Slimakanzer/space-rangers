package com.spaceRangers.config.rootConfigs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan
public class RootConfig extends WebMvcConfigurerAdapter {
}

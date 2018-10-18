package com.spaceRangers.config;
import com.spaceRangers.config.web.WebConfig;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

public class WebApplicationInitialiser extends AbstractDispatcherServletInitializer{

    @Override
    protected WebApplicationContext createServletApplicationContext() {
        System.out.println("Init web context");

        AnnotationConfigWebApplicationContext servletConfig = new AnnotationConfigWebApplicationContext();
        servletConfig.register(WebConfig.class);

        return servletConfig;
    }

    @Override
    protected WebApplicationContext createRootApplicationContext() {
        System.out.println("Init application context");
        AnnotationConfigWebApplicationContext rootConfig = new AnnotationConfigWebApplicationContext();
        rootConfig.register(RootConfig.class);

        return rootConfig;
    }

    @Override
    protected String[] getServletMappings() {

        return new String[]{"/"};
    }
}
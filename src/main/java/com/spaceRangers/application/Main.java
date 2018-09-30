package com.spaceRangers.application;

import com.spaceRangers.entities.GroupAuthorityEntity;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {
    public static void main(String[] args){
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:applicationContext.xml");
        ctx.refresh();
    }
}

package com.spaceRangers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController{

    @RequestMapping("/")
    public String getIndex(){

        return "login";
    }

    @RequestMapping("/login")
    public String getLogin(){
        return "login";
    }

    @RequestMapping("/test")
    public String getTest(){
        return "test";
    }

    @RequestMapping("/denied")
    public String getFailed(){
        return "denied";
    }

}

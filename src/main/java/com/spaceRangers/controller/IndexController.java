package com.spaceRangers.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spaceRangers.entities.UserAccountEntity;
import com.spaceRangers.entities.UsersEntity;
import com.spaceRangers.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.awt.*;
import java.io.IOException;

@Controller
public class IndexController{

    @Autowired
    RegistrationService registrationService;

    @RequestMapping("/")
    public String getIndex(){

        return "login";
    }

    @RequestMapping("/login")
    public String getLogin(){
        return "login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registrations(
            @RequestBody String users, BindingResult bindingResult, Model model){

        UserAccountEntity user = null;
        try {
            user = new ObjectMapper().readValue(users, UserAccountEntity.class);
            UsersEntity usersEntity = registrationService.createUser(user);


        } catch (IOException e) {
            e.printStackTrace();
        }

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

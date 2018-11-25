package com.spaceRangers.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spaceRangers.entities.UserAccountEntity;
import com.spaceRangers.entities.UsersEntity;
import com.spaceRangers.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.security.Principal;

@Controller
public class IndexController{

    @Autowired
    RegistrationService registrationService;

    @RequestMapping("/")
    public String getIndex(@AuthenticationPrincipal Principal principal){
        if(principal == null){
            return "login";
        }else {
            return "test";
        }
    }

    @RequestMapping("/login")
    public String getLogin(){
        return "login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registrations(BindingResult bindingResult, Model model){

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

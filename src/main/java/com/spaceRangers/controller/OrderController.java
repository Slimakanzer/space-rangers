package com.spaceRangers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class OrderController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String getOrderPage(Model model){
        model.addAttribute("kek", new String("SUka bluat"));



        return "index";
    }
}

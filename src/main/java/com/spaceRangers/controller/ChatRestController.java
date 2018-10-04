package com.spaceRangers.controller;

import com.spaceRangers.entities.ChatEntity;
import com.spaceRangers.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/chat")
public class ChatRestController {

    @Qualifier("testJpaServiceImpl")
    @Autowired
    TestService test;

    @RequestMapping(method = RequestMethod.GET, value = "/chat_with_fraction")
    public ResponseEntity<ChatEntity> chatWithFraction(@RequestParam int id){


        ChatEntity chat = test.getChat(id);
        return new ResponseEntity<ChatEntity>(chat, HttpStatus.OK);
    }
}

package com.spaceRangers.controller;

import com.spaceRangers.entities.ChatEntity;
import com.spaceRangers.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/api/chat")
public class ChatRestController {

    @Qualifier("testJpaServiceImpl")
    @Autowired
    TestService test;

    @RequestMapping(method = RequestMethod.GET, value = "/chat/{id}")
    @ResponseBody
    public ResponseEntity<ChatEntity> chatWithFraction(@PathVariable("id") int id){
        ChatEntity chat;
        try {
            chat = test.getChat(id);
            return new ResponseEntity<ChatEntity>(chat, HttpStatus.OK);
        }catch (NoSuchElementException e){
            chat = null;
            return new ResponseEntity<ChatEntity>(chat, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/chat")
    @ResponseBody
    public ResponseEntity<List<ChatEntity>> getListChat(){
        return new ResponseEntity<List<ChatEntity>>(test.getListChat(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/chat")
    @ResponseBody
    public ResponseEntity<ChatEntity> insertChat(@RequestBody ChatEntity chatEntity){
        test.insertChat(chatEntity);
        return new ResponseEntity<ChatEntity>(chatEntity, HttpStatus.OK);
    }
}

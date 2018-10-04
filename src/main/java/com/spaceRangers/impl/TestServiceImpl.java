package com.spaceRangers.impl;

import com.spaceRangers.entities.ChatEntity;
import com.spaceRangers.repository.ChatEntityRepository;
import com.spaceRangers.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("testJpaServiceImpl")
@Transactional
public class TestServiceImpl implements TestService {


    @Autowired
    private ChatEntityRepository test;

    @Override
    public ChatEntity getChat(int id) {
        return test.findById(id).get();
    }

    @Override
    public ChatEntity insertChat(ChatEntity chat) {
        test.save(chat);
        return chat;
    }

    @Override
    public List<ChatEntity> getListChat(){
        return test.findAll();
    }
}

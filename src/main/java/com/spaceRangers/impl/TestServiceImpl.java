package com.spaceRangers.impl;

import com.spaceRangers.entities.ChatEntity;
import com.spaceRangers.repository.ChatEntityRepository;
import com.spaceRangers.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("testJpaServiceImpl")
@Transactional
public class TestServiceImpl implements TestService {


    @Autowired
    private ChatEntityRepository test;

    public ChatEntity getChat(int id) {
        return test.findById(id).get();
    }

    public boolean insertChat(ChatEntity chat) {
        return false;
    }
}

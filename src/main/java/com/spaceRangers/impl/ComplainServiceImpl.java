package com.spaceRangers.impl;

import com.spaceRangers.entities.*;
import com.spaceRangers.repository.ComplainRepository;
import com.spaceRangers.repository.MessagesRepository;
import com.spaceRangers.repository.UserRepository;
import com.spaceRangers.service.ChatService;
import com.spaceRangers.service.ComplainService;
import com.spaceRangers.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Collection;


@Service("complainService")
public class ComplainServiceImpl implements ComplainService {

    private final ComplainRepository complainRepository;

    @Autowired
    MessagesRepository messagesRepository;

    @Autowired
    ChatService chatService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RegistrationService registrationService;

    @Autowired
    public ComplainServiceImpl(ComplainRepository complainRepository) {
        this.complainRepository = complainRepository;
    }

    @Override
    @Transactional
    public ComplainEntity createComplain(ComplainEntity complainEntity) {
        complainEntity.setDate(new Date(new java.util.Date().getTime()));
        StateComplainEntity stateComplainEntity = new StateComplainEntity();
        stateComplainEntity.setName("new");
        complainEntity.setStateComplain(stateComplainEntity);
        messagesRepository.save(complainEntity.getMessage());
        complainRepository.save(complainEntity);
        return complainEntity;
    }

    @Override
    @Transactional
    public ComplainEntity updateComplain(ComplainEntity complainEntity) {
        complainRepository.save(complainEntity);
        return complainEntity;
    }

    @Override
    @Transactional
    public ChatEntity startProcessionComplain(ComplainEntity complainEntity, User user) {
        UserAccountEntity usersEntity = registrationService.getUserAccount(user.getUsername());
        UsersEntity supprot = usersEntity.getUser();


        UsersEntity sender = userRepository.findById(complainEntity.getMessage().getIdUser()).get();

        ChatEntity chatEntity = new ChatEntity();
        chatEntity.setName("Support chat"+complainEntity.getMessage().getIdUser()+supprot.getId()+complainEntity.getDate().getTime());

        chatEntity.setDate(new Date(new java.util.Date().getTime()));
        chatService.createChat(chatEntity);

        supprot.getChats().add(chatEntity);
        sender.getChats().add(chatEntity);
        chatEntity.getUsers().add(supprot);
        chatEntity.getUsers().add(sender);
        complainEntity.getMessage().setChat(chatEntity);
        chatEntity.getMessages().add(complainEntity.getMessage());
        return chatEntity;
    }

    public Collection<ComplainEntity> getComplains(){
        return complainRepository.findAll();
    }
}

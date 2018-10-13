package com.spaceRangers.impl;

import com.spaceRangers.entities.*;
import com.spaceRangers.repository.ChatRepository;
import com.spaceRangers.repository.ChatUserRepository;
import com.spaceRangers.repository.MessagesRepository;
import com.spaceRangers.repository.UserRepository;
import com.spaceRangers.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("chatService")
public class ChatServiceImpl implements ChatService {

    @Autowired
    ChatRepository chatRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ChatUserRepository chatUserRepository;

    @Autowired
    MessagesRepository messagesRepository;

    @Override
    public boolean createChat(ChatEntity chat) {
        chatRepository.save(chat);
        return true;
    }

    @Override
    public ChatEntity getChat(int idChat) {
        return chatRepository.findById(idChat).get();
    }

    @Override
    public List<ChatEntity> getChatsUser(int idUser) {
        // TODO перенести в репозиторий
        List<ChatEntity> listChats = new ArrayList<>();
        chatUserRepository
                .findAll()
                .stream()
                .filter(chatUserEntity -> chatUserEntity.getIdUser() == idUser)
                .forEach(e ->{
                    listChats.add(
                            chatRepository.findById(
                                    e.getIdChat()
                            ).get()
                    );
                });
        return listChats;
    }

    @Override
    public List<UsersEntity> getUsersInChat(int idChat) {
        // TODO перенести в репозиторий
        List<String> listUsers = new ArrayList<>();
        chatUserRepository
                .findAll()
                .stream()
                .filter(chatUserEntity -> chatUserEntity.getIdChat() == idChat)
                .forEach(chatUserEntity ->
                        listUsers.add(
                                userRepository.findById(
                                        chatUserEntity.getIdUser()
                                ).get().getLogin()
                        )
                );
        return null;
    }

    @Override
    public boolean createChatUser(ChatUserEntity chatUser) {
        chatUserRepository.save(chatUser);
        return true;
    }

    @Override
    public boolean dropChatUser(ChatUserEntity chatUser) {
        chatUserRepository.delete(chatUser);
        return true;
    }

    @Override
    public List<MessagesEntity> getMessagesOfChat(int idChat) {
        // TODO перенести в репозиторий
        return messagesRepository
                .findAll()
                .stream()
                .filter(messagesEntity -> messagesEntity.getIdChat() == idChat)
                .collect(Collectors.toList());
    }

    @Override
    public MessagesEntity getMessageById(int idMessage) {
        return messagesRepository.findById(idMessage).get();
    }

    @Override
    public boolean createMessages(MessagesEntity message) {
        messagesRepository.save(message);
        return true;
    }
}

package com.spaceRangers.impl;

import com.spaceRangers.entities.*;
import com.spaceRangers.repository.ChatRepository;
import com.spaceRangers.repository.ChatUserRepository;
import com.spaceRangers.repository.MessagesRepository;
import com.spaceRangers.repository.UserRepository;
import com.spaceRangers.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service("chatService")
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;

    private final UserRepository userRepository;

    private final ChatUserRepository chatUserRepository;

    private final MessagesRepository messagesRepository;

    @Autowired
    public ChatServiceImpl(ChatRepository chatRepository, UserRepository userRepository, ChatUserRepository chatUserRepository, MessagesRepository messagesRepository) {
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
        this.chatUserRepository = chatUserRepository;
        this.messagesRepository = messagesRepository;
    }

    @Transactional
    public ChatEntity createChat(ChatEntity chat) {

        chatRepository.save(chat);

        return chat;
    }

    @Transactional
    public ChatEntity updateChat(ChatEntity chat){
        chatRepository.save(chat);
        return chat;
    }

    /**
     * Получение чата по id
     *
     * @param name
     * @return
     */
    @Override
    public ChatEntity getChat(String name) {
        return chatRepository.findChatEntityByName(name);
    }

    @Override
    public ChatEntity getChat(int idChat) {
        return chatRepository.findById(idChat).get();
    }

    @Override
    @Transactional
    public List<ChatEntity> getChatsUser(int idUser) {
        ArrayList arrayList = new ArrayList<>();
        arrayList.addAll(
                userRepository
                    .findById(idUser).get()
                    .getChats()

        );
        return arrayList;
    }

    @Transactional
    @Override
    public boolean dropChat(ChatEntity chatEntity) {
        chatRepository.delete(chatEntity);
        return true;
    }

    @Override
    public List<UsersEntity> getUsersInChat(int idChat) {
        try {
            ArrayList users = new ArrayList();
            users.addAll(
                    getChat(idChat)
                            .getUsers()
            );
            return users;
        }catch (Exception e){
            throw new NoSuchElementException();
        }
    }

    @Override
    @Deprecated
    public ChatUserEntity createChatUser(ChatUserEntity chatUser) {
        chatUserRepository.save(chatUser);
        return chatUser;
    }

    @Override
    @Deprecated
    public boolean dropChatUser(ChatUserEntity chatUser) {
        chatUserRepository.delete(chatUser);
        return true;
    }

    @Override
    public List<MessagesEntity> getMessagesOfChat(int idChat) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(
                getChat(idChat).getMessages()
        );
        return arrayList;
    }

    @Override
    public MessagesEntity getMessage(int idMessage) {
        return messagesRepository.findById(idMessage).get();
    }

    @Override
    @Transactional
    public MessagesEntity createMessages(MessagesEntity message) {
        messagesRepository.save(message);
        return message;
    }

    @Override
    public boolean userInChat(UsersEntity user, ChatEntity chat) {
        Iterator iterator = chat.getUsers().iterator();

        while (iterator.hasNext()){
            UsersEntity userInChat = (UsersEntity) iterator.next();

            if (userInChat.getId().equals(user.getId())){
                return true;
            }
        }

        return false;
    }
}

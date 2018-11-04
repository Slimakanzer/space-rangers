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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    public ChatUserEntity createChatUser(ChatUserEntity chatUser) {
        chatUserRepository.save(chatUser);
        return chatUser;
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
                .filter(messagesEntity -> messagesEntity.getChat().getId() == idChat)
                .collect(Collectors.toList());
    }

    @Override
    public MessagesEntity getMessage(int idMessage) {
        return messagesRepository.findById(idMessage).get();
    }

    @Override
    public MessagesEntity createMessages(MessagesEntity message) {
        messagesRepository.save(message);
        return message;
    }
}

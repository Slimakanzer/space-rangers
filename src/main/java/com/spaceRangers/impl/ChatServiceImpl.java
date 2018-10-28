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
    public ChatEntity createChat(Map<String, Object> mapObject) {

        ChatEntity chat = (ChatEntity) mapObject.get("chat");
        UsersEntity user = (UsersEntity) mapObject.get("user");

        chatRepository.save(chat);

        ChatUserEntity chatUserEntity = new ChatUserEntity();
        chatUserEntity.setIdChat(chat.getId());
        chatUserEntity.setIdUser(user.getId());

        chatUserRepository.save(chatUserEntity);

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
                .filter(messagesEntity -> messagesEntity.getChatByIdChat().getId() == idChat)
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

package com.spaceRangers.service;

import com.spaceRangers.entities.ChatEntity;
import com.spaceRangers.entities.ChatUserEntity;
import com.spaceRangers.entities.MessagesEntity;
import com.spaceRangers.entities.UsersEntity;

import java.util.List;
import java.util.Map;

public interface ChatService {

    /**
     * Создание чата
     * @param
     * @return
     */
    ChatEntity createChat(Map<String, Object> mapObject);

    /**
     * Получение чата по id
     * @param idChat
     * @return
     */
    ChatEntity getChat(int idChat);


    /**
     * Получение чата по id
     * @param name
     * @return
     */
    ChatEntity getChatByName(String name);


    void dropChat(ChatEntity chatEntity);

    /**
     * Получение чатов пользователя
     * @param idUser
     * @return
     */
    List<ChatEntity> getChatsUser(int idUser);

    /**
     * Получение списка логинов пользователей в чате
     * @param
     * @return
     */
    List<UsersEntity> getUsersInChat(int idChat);

    /**
     * Добавление пользователя в чат
     * @param chatUser
     * @return
     */
    boolean createChatUser(ChatUserEntity chatUser);

    /**
     * Удаление пользователя из чата
     * @param chatUser
     * @return
     */
    boolean dropChatUser(ChatUserEntity chatUser);

    /**
     *Список сообщений чата
     * @param
     * @return
     */
    List<MessagesEntity> getMessagesOfChat(int idChat);

    /**
     *
     * Получение сообщения по id
     * @param idMessage
     * @return
     */
    MessagesEntity getMessageById(int idMessage);

    /**
     * Создание сообщения
     * @param message
     * @return
     */
    boolean createMessages(MessagesEntity message);
}

package com.spaceRangers.service;

import com.spaceRangers.entities.ChatEntity;
import com.spaceRangers.entities.ChatUserEntity;
import com.spaceRangers.entities.MessagesEntity;
import com.spaceRangers.entities.UsersEntity;

import java.util.List;

public interface ChatService {

    /**
     * Создание чата
     * @param chat
     * @return
     */
    boolean createChat(ChatEntity chat);

    /**
     * Получение чата по id
     * @param idChat
     * @return
     */
    ChatEntity getChat(int idChat);

    /**
     * Получение чатов пользователя
     * @param idUser
     * @return
     */
    List<ChatEntity> getChatsUser(int idUser);

    /**
     * Получение списка логинов пользователей в чате
     * @param idChat
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
     * @param idChat
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

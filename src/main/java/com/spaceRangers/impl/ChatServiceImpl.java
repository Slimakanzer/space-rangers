package com.spaceRangers.impl;

import com.spaceRangers.entities.*;
import com.spaceRangers.service.ChatService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ChatServiceImpl implements ChatService {



    /**
     * Создание чата между двумя пользователями
     *
     * @param chat         {@link ChatEntity сущность чата}, которую необходимо создать
     * @param userFirst    {@link UsersEntity сущность пользователя}, который создает чат
     * @param idUserSecond {@link UsersEntity#id идентификатор пользователя}, с кем необходимо создать чат
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see ChatEntity
     * @see UsersEntity
     */
    @Override
    public boolean createChatWithUser(ChatEntity chat, UsersEntity userFirst, int idUserSecond) {
        return false;
    }

    /**
     * Создание чата между двумя пользователями
     *
     * @param chat            {@link ChatEntity сущность чата}, которую необходимо создать
     * @param userFirst       {@link UsersEntity сущность пользователя}, который создает чат
     * @param loginUserSecond {@link UsersEntity#login логин пользователя}, с которым создается чат
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see ChatEntity
     * @see UsersEntity
     */
    @Override
    public boolean createChatWithUser(ChatEntity chat, UsersEntity userFirst, String loginUserSecond) {
        return false;
    }

    /**
     * Создание чата между двумя пользователями
     *
     * @param chat       {@link ChatEntity сущность чата}, которую необходимо создать
     * @param userFirst  {@link UsersEntity сущность пользователя}, который создает чат
     * @param userSecond {@link UsersEntity сущность пользователя}, с которым создается чат
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see ChatEntity
     * @see UsersEntity
     */
    @Override
    public boolean createChatWithUser(ChatEntity chat, UsersEntity userFirst, UsersEntity userSecond) {
        return false;
    }

    /**
     * Создание чата внутри фракции
     *
     * @param chat     {@link ChatEntity сущность чата}, которую необходимо создать
     * @param fraction {@link FractionEntity сущность фракции}, внутри которой создается чат
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see ChatEntity
     * @see FractionEntity
     */
    @Override
    public boolean creatChatWithFraction(ChatEntity chat, FractionEntity fraction) {
        return false;
    }

    /**
     * Получение чата между двумя пользователями
     *
     * @param userFirst   {@link UsersEntity сущность пользователя}, который получает чат
     * @param idUserFirst {@link UsersEntity#id идентификатор пользователя}, с которым получают чат
     * @return возвращает {@link ChatEntity чат} между пользователями
     * @see UsersEntity
     * @see ChatEntity
     */
    @Override
    public ChatEntity getChatWithUser(UsersEntity userFirst, int idUserFirst) {
        return null;
    }

    /**
     * Получение чата между двумя пользователями
     *
     * @param userFirst      {@link UsersEntity сущность пользователя}, который получает чат
     * @param loginUserFirst {@link UsersEntity#login логин пользователя}, с которым получают чат
     * @return возвращает {@link ChatEntity чат} между пользователями
     * @see UsersEntity
     * @see ChatEntity
     */
    @Override
    public ChatEntity getChatWithUser(UsersEntity userFirst, String loginUserFirst) {
        return null;
    }

    /**
     * Получение чата между двумя пользователями
     *
     * @param userFirst  {@link UsersEntity сущность пользователя}, который получает чат
     * @param userSecond {@link UsersEntity сущность пользователя}, с которым получают чат
     * @return возвращает {@link ChatEntity чат} между пользователями
     * @see UsersEntity
     * @see ChatEntity
     */
    @Override
    public ChatEntity getChatWithUser(UsersEntity userFirst, UsersEntity userSecond) {
        return null;
    }

    /**
     * Получение чата фракции
     *
     * @param fraction {@link FractionEntity сущность фракции}, чат которой необходимо получить
     * @return возвращает {@link ChatEntity чат фракции}
     * @see FractionEntity
     * @see FractionEntity#id
     * @see ChatEntity
     */
    @Override
    public ChatEntity getChatWithFraction(FractionEntity fraction) {
        return null;
    }

    /**
     * Отправление сообщения в чат
     *
     * @param messagesEntity {@link MessagesEntity сущность сообщения}, котрое необходимо отправить
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see MessagesEntity
     */
    @Override
    public boolean sendMessage(MessagesEntity messagesEntity) {
        return false;
    }

    /**
     * Создание голосования в чате
     *
     * @param voting {@link VotingEntity сущность голосования}, которую необходимо создать в чате
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see VotingEntity
     */
    @Override
    public boolean createVoting(VotingEntity voting) {
        return false;
    }

    /**
     * Удаления голосования в чате
     *
     * @param voting {@link VotingEntity сущность голосования}, которую необходимо удалить в чате
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see VotingEntity
     */
    @Override
    public boolean dropVoting(VotingEntity voting) {
        return false;
    }

    /**
     * Создания варианта выбора в голосовании
     *
     * @param result {@link ResultsEntity сущность выбора}, которую необходимо добавить в голосование
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see ResultsEntity
     * @see VotingEntity
     */
    @Override
    public boolean createResultsVoting(ResultsEntity result) {
        return false;
    }

    /**
     * Учёт голоса пользователя
     *
     * @param vote {@link VoteEntity сущность голоса пользователя}, которую необходимо учесть
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see VoteEntity
     * @see ResultsEntity
     * @see VotingEntity
     */
    @Override
    public boolean vote(VoteEntity vote) {
        return false;
    }

    /**
     * Получение наиболее часто выбираемого варианта выбора
     *
     * @param voting {@link VotingEntity сущность голосования}, из которого необходимо получить победный вариант
     * @return возвращает {@link ResultsEntity вариант}, который победил в голосовании
     * @see ResultsEntity
     * @see VotingEntity
     */
    @Override
    public ResultsEntity getWinnerResult(VotingEntity voting) {
        return null;
    }
}

package com.spaceRangers.service;

import com.spaceRangers.entities.*;

/**
 *  Интерфейс сервиса чата для создания чатов между пользователями
 *  , между пользователями внутри фракции, управление голосованиями внутри чатов
 *  и возможность голосования.
 * @version 1.0
 * @author Ларочкин Г.И.
 */
public interface ChatService {

    /**
     * Создание чата между двумя пользователями
     * @param chat {@link ChatEntity сущность чата}, которую необходимо создать
     * @param userFirst {@link UsersEntity сущность пользователя}, который создает чат
     * @param idUserSecond {@link UsersEntity#id идентификатор пользователя}, с кем необходимо создать чат
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see ChatEntity
     * @see UsersEntity
     */
    boolean createChatWithUserById(ChatEntity chat, UsersEntity userFirst, int idUserSecond);

    /**
     * Создание чата между двумя пользователями
     * @param chat {@link ChatEntity сущность чата}, которую необходимо создать
     * @param userFirst {@link UsersEntity сущность пользователя}, который создает чат
     * @param loginUserSecond {@link UsersEntity#login логин пользователя}, с которым создается чат
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see ChatEntity
     * @see UsersEntity
     */
    boolean createChatWithUserByLogin(ChatEntity chat, UsersEntity userFirst, String loginUserSecond);

    /**
     * Создание чата внутри фракции
     * @param chat {@link ChatEntity сущность чата}, которую необходимо создать
     * @param idFraction {@link FractionEntity#id идентификатор фракции}, внутри которой создается чат
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see ChatEntity
     * @see FractionEntity#id
     */
    boolean creatChatWithFraction(ChatEntity chat, int idFraction);

    /**
     * Получение чата между двумя пользователями
     * @param userFirst {@link UsersEntity сущность пользователя}, который получает чат
     * @param idUserFirst {@link UsersEntity#id идентификатор пользователя}, с которым получают чат
     * @return возвращает {@link ChatEntity чат} между пользователями
     * @see UsersEntity
     * @see ChatEntity
     */
    ChatEntity getChatWithUserById(UsersEntity userFirst, int idUserFirst);

    /**
     * Получение чата между двумя пользователями
     * @param userFirst {@link UsersEntity сущность пользователя}, который получает чат
     * @param loginUserFirst {@link UsersEntity#login логин пользователя}, с которым получают чат
     * @return возвращает {@link ChatEntity чат} между пользователями
     * @see UsersEntity
     * @see ChatEntity
     */
    ChatEntity getChatWithUserByLogin(UsersEntity userFirst, String loginUserFirst);

    /**
     * Получение чата фракции
     * @param idFraction {@link FractionEntity#id идентификатор фракции}, чат которой необходимо получить
     * @return возвращает {@link ChatEntity чат фракции}
     * @see FractionEntity
     * @see FractionEntity#id
     * @see ChatEntity
     */
    ChatEntity getChatWithFraction(int idFraction);

    /**
     * Отправление сообщения в чат
     * @param messagesEntity {@link MessagesEntity сущность сообщения}, котрое необходимо отправить
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see MessagesEntity
     */
    boolean sendMessage(MessagesEntity messagesEntity);

    /**
     * Создание голосования в чате
     * @param voting {@link VotingEntity сущность голосования}, которую необходимо создать в чате
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see VotingEntity
     */
    boolean createVoting(VotingEntity voting);

    /**
     * Удаления голосования в чате
     * @param voting {@link VotingEntity сущность голосования}, которую необходимо удалить в чате
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see VotingEntity
     */
    boolean dropVoting(VotingEntity voting);

    /**
     * Создания варианта выбора в голосовании
     * @param result {@link ResultsEntity сущность выбора}, которую необходимо добавить в голосование
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see ResultsEntity
     * @see VotingEntity
     */
    boolean createResultsVoting(ResultsEntity result);

    /**
     * Учёт голоса пользователя
     * @param vote {@link VoteEntity сущность голоса пользователя}, которую необходимо учесть
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see VoteEntity
     * @see ResultsEntity
     * @see VotingEntity
     */
    boolean vote(VoteEntity vote);

    /**
     * Получение наиболее часто выбираемого варианта выбора
     * @param voting {@link VotingEntity сущность голосования}, из которого необходимо получить победный вариант
     * @return возвращает {@link ResultsEntity вариант}, который победил в голосовании
     * @see ResultsEntity
     * @see VotingEntity
     */
    ResultsEntity getWinnerResult(VotingEntity voting);


}

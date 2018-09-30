package com.spaceRangers.service;

import com.spaceRangers.entities.*;

/**
 * Интерфейс сервиса работы с фракцией, принятие новых пользователей,
 * постановка и изменение задач фракции,
 * изменение приватности фракции.
 * @version 1.0
 * @author Ларочкин Г.И.
 */
public interface FractionActivityService {

    /**
     * Отправка заявление на вступление во фракцию
     * @param user {@link UsersEntity сущность пользователя}, который подаёт заявку
     * @param fraction {@link FractionEntity сущность фракции}, в которую подаёт заявку
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see UsersEntity
     * @see FractionEntity
     */
    boolean submissionUserToFraction(UsersEntity user, FractionEntity fraction);

    /**
     * Выход из фракции пользователем
     * @param user {@link UsersEntity сущность пользователя}, который выходит из фракции
     * @param fraction {@link FractionEntity сущность фракции}, из которой выходит
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see UsersEntity
     * @see FractionEntity
     */
    boolean exitUserFromFraction(UsersEntity user, FractionEntity fraction);

    /**
     * Принятие заявки на вступление во фракцию
     * @param fraction {@link FractionEntity сущность фракции}, в которой принимается пользователь
     * @param idUser {@link UsersEntity#id идентификатор пользователя}, которого необходимо принять
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see UsersEntity
     * @see UsersEntity#id
     * @see FractionEntity
     */
    boolean acceptUserToFraction(FractionEntity fraction, int idUser);

    /**
     * Принятие заявки на вступление во фракцию
     * @param fraction {@link FractionEntity сущность фракции}, в которой принимается пользователь
     * @param loginUser {@link UsersEntity#login логин пользователя}, которого необходимо принять
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see UsersEntity
     * @see UsersEntity#login
     * @see FractionEntity
     */
    boolean acceptUserToFraction(FractionEntity fraction, String loginUser);

    /**
     * Принятие заявки на вступление во фракцию
     * @param fraction {@link FractionEntity сущность фракции}, в которой принимается пользователь
     * @param user {@link UsersEntity сущность пользователя}, которого необходимо принять
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see UsersEntity
     * @see FractionEntity
     */
    boolean acceptUserToFraction(FractionEntity fraction, UsersEntity user);

    /**
     * Добавление задачи фракции
     * @param task {@link TaskEntity задача}, которую необходимо добавть
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see TaskEntity
     * @see FractionEntity
     */
    boolean addTask(TaskEntity task);

    /**
     * Удаление задачи фракции
     * @param task {@link TaskEntity задача}, которую необходимо удалить
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see TaskEntity
     * @see FractionEntity
     */
    boolean removeTask(TaskEntity task);

    /**
     * Утверждение задачи фракции
     * @param task {@link TaskEntity задача}, которую необходимо утвердить
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see TaskEntity
     * @see FractionEntity
     */
    boolean acceptTask(TaskEntity task);

    /**
     * Изменение видимости(какая группа может видеть) задачи фракции
     * @param task {@link TaskEntity задача}, видимость которой необходмо изменить
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see TaskEntity
     * @see FractionEntity
     */
    boolean setScopeTask(TaskEntity task);

    /**
     * Получение видимости(какая группа может видеть) задачи фракции
     * @param task {@link TaskEntity задача}, видимость которой необходмо получить
     * @return возвращает {@link StatePrivacyEntity сущность состояния приватности}
     * @see TaskEntity
     * @see StatePrivacyEntity
     * @see FractionEntity
     */
    StatePrivacyEntity getScopeTask(TaskEntity task);

    /**
     * Изменение видимости(какая группа может видеть) пользователя внутри фракции
     * @param statePrivacy сущность {@link StatePrivacyEntity состояния приватности}, которую необходимо установить
     * @param idUser {@link UsersEntity#id идентификатор пользователя}, которому необходимо изменить
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see StatePrivacyEntity
     * @see UsersEntity
     * @see UsersEntity#id
     * @see FractionEntity
     */
    boolean setScopeUserInFraction(StatePrivacyEntity statePrivacy, int idUser);

    /**
     * Изменение видимости(какая группа может видеть) пользователя внутри фракции
     * @param statePrivacy сущность {@link StatePrivacyEntity состояния приватности}, которую необходимо установить
     * @param user {@link UsersEntity сущность пользователя}, которому необходимо изменить
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see StatePrivacyEntity
     * @see UsersEntity
     * @see FractionEntity
     */
    boolean setScopeUserInFraction(StatePrivacyEntity statePrivacy, UsersEntity user);

    /**
     * Изменение политической системы фракции
     * @param fraction {@link FractionEntity сущность фракции}, политику которой необходимо изменить
     * @param politics {@link PoliticsEntity сущность политической системы}
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see FractionEntity
     * @see PoliticsEntity
     */
    boolean changePoliticsFraction(FractionEntity fraction, PoliticsEntity politics);
}

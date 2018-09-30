package com.spaceRangers.service;

import com.spaceRangers.entities.GroupsEntity;
import com.spaceRangers.entities.StateUserEntity;
import com.spaceRangers.entities.UsersEntity;

import java.util.List;

/**
 * Интерфейс сервиса управления пользователями,
 * изменение их статуса (бан, кик и т.д.) и удаление из системы
 * @version 1.0
 * @author Ларочкин Г.И.
 */
public interface UserAdministrationService {

    /**
     * Изменение {@link UsersEntity#state статуса} пользователя по идентификатору
     * по {@link StateUserEntity#id идентификатору} существующих состояний
     * @param idUser {@link UsersEntity#id идентификатор пользователя}, состояние которого надо изменить
     * @param state {@link StateUserEntity сущность состояния}, которое надо установить
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see UsersEntity
     * @see UsersEntity#id
     * @see StateUserEntity
     */
    boolean setUserState(int idUser, StateUserEntity state);

    /**
     * Изменение {@link UsersEntity#state статуса} пользователя по логину
     * по {@link StateUserEntity#id идентификатору} существующих состояний
     * @param loginUser {@link UsersEntity#login логин пользователя}, состояние которого надо изменить
     * @param state {@link StateUserEntity сущность состояния}, которое надо установить
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see UsersEntity
     * @see UsersEntity#login
     * @see StateUserEntity
     */
    boolean setUserState(String loginUser, StateUserEntity state);

    /**
     * Изменение {@link UsersEntity#state статуса} пользователя по логину
     * по {@link StateUserEntity#id идентификатору} существующих состояний
     * @param user {@link UsersEntity сущность пользователя}, состояние которого надо изменить
     * @param state {@link StateUserEntity сущность состояния}, которое надо установить
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see UsersEntity
     * @see StateUserEntity
     */
    boolean setUserState(UsersEntity user, StateUserEntity state);

    /**
     *  Удаление пользователя из системы по идентификатору
     * @param idUser {@link UsersEntity#id идентификатор пользователя}, которого надо удалить
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see UsersEntity
     * @see UsersEntity#id
     */
    boolean dropUser(int idUser);

    /**
     *  Удаление пользователя из системы пологину
     * @param loginUser {@link UsersEntity#login логин пользователя}, которого надо удалить
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see UsersEntity
     * @see UsersEntity#login
     */
    boolean dropUser(String loginUser);

    /**
     *  Удаление пользователя из системы пологину
     * @param user {@link UsersEntity сущность пользователя}, которого надо удалить
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see UsersEntity
     * @see UsersEntity#login
     */
    boolean dropUser(UsersEntity user);

    /**
     * Получение групп пользователя по идентификатору
     * @param idUser {@link UsersEntity#id идентификатор пользователя}
     * @return список {@link GroupsEntity групп} пользователя
     * @see UsersEntity
     * @see UsersEntity#id
     * @see GroupsEntity
     */
    List<GroupsEntity> getUserGroup(int idUser);

    /**
     * Получение групп пользователя по идентификатору
     * @param loginUser {@link UsersEntity#login логин пользователя}
     * @return список {@link GroupsEntity групп} пользователя
     * @see UsersEntity
     * @see UsersEntity#login
     * @see GroupsEntity
     */
    List<GroupsEntity> getUserGroup(String loginUser);

    /**
     * Получение групп пользователя по идентификатору
     * @param user {@link UsersEntity сущность пользователя}
     * @return список {@link GroupsEntity групп} пользователя
     * @see UsersEntity
     * @see GroupsEntity
     */
    List<GroupsEntity> getUserGroup(UsersEntity user);

    /**
     * Удаление группы пользователя по идентификатору
     * @param idUser {@link UsersEntity#id идентификатор пользователя}
     * @param group {@link GroupsEntity сущность группы}
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see UsersEntity
     * @see UsersEntity#id
     * @see GroupsEntity
     */
    boolean dropUserGroup(int idUser, GroupsEntity group);

    /**
     * Удаление группы пользователя по идентификатору
     * @param loginUser {@link UsersEntity#login логин пользователя}
     * @param group {@link GroupsEntity сущность группы}
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see UsersEntity
     * @see UsersEntity#login
     * @see GroupsEntity
     */
    boolean dropUserGroup(String loginUser, GroupsEntity group);

    /**
     * Удаление группы пользователя по идентификатору
     * @param user {@link UsersEntity сущность пользователя}
     * @param group {@link GroupsEntity сущность группы}
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see UsersEntity
     * @see GroupsEntity
     */
    boolean dropUserGroup(UsersEntity user, GroupsEntity group);

    /**
     * Добавление группы пользователю по идентификатору
     * @param idUser {@link UsersEntity#id идентификатор пользователя}
     * @param group {@link GroupsEntity сущность группы}
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see UsersEntity
     * @see UsersEntity#id
     * @see GroupsEntity
     */
    boolean addUserGroup(int idUser, GroupsEntity group);

    /**
     * Добавление группы пользователю по идентификатору
     * @param loginUser {@link UsersEntity#login логин пользователя}
     * @param group {@link GroupsEntity сущность группы}
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see UsersEntity
     * @see UsersEntity#login
     * @see GroupsEntity
     */
    boolean addUserGroup(String loginUser, GroupsEntity group);

    /**
     * Добавление группы пользователю по идентификатору
     * @param user {@link UsersEntity сущность пользователя}
     * @param group {@link GroupsEntity сущность группы}
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see UsersEntity
     * @see UsersEntity#login
     * @see GroupsEntity
     */
    boolean addUserGroup(UsersEntity user, GroupsEntity group);
}

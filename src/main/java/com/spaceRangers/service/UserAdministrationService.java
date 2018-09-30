package com.spaceRangers.service;

import com.spaceRangers.entities.StateUserEntity;
import com.spaceRangers.entities.UsersEntity;

/**
 * Интерфейс сервиса управления пользователями,
 * изменение их статуса (бан, кик и т.д.) и удаление из системы
 * @version 1.0
 * @author Ларочкин Г.И.
 */
public interface UserAdministrationService {

    /**
     * Изменение {@link UsersEntity#state статуса} пользователя
     * по {@link StateUserEntity#id идентификатору} существующих состояний
     * @param user сущность пользователя, состояние которого надо изменить
     * @param statusId идентификатор состояние, которое надо установить
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see UsersEntity
     * @see StateUserEntity#id
     */
    boolean setUserStatus(UsersEntity user, int statusId);

    /**
     *  Удаление пользователя из системы
     * @param user сущность пользователя, которого необходимо удалить
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see UsersEntity
     */
    boolean dropUser(UsersEntity user);
}

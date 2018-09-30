package com.spaceRangers.service;

import com.spaceRangers.entities.UsersEntity;

/**
 *  Интерфейс сервиса регистрации, аутентификации и редактирование профиля пользователя
 * @author Ларочкин Г.И.
 * @version 1.0
 */
public interface UserProfileService {

    /**
     *  Регистрация пользователя в системе, если пользователь с таким {@link UsersEntity#login логином} отсутствует
     * @param user подаётся сущность пользователя
     * @return Возвращает сущность пользователя зарегестрированого, если всё удачно, иначе {@link null}
     * @see UsersEntity
     */
    UsersEntity register(UsersEntity user);

    /**
     *  Сохранение изменений редактирование профиля пользователя
     * @param user сущность пользователя, которую необходимо сохранить
     * @return возвращает true, если транзакция успешно прошла, иначе false
     * @see UsersEntity
     */
    boolean save(UsersEntity user);

    /**
     *  Аутентификация пользователя в системе по {@link UsersEntity#login логину} и {@link UsersEntity#password паролю}
     * @param login логинин пользователя
     * @param password пароль пользователя
     * @return возвращает сущность пользователя, если всё удачно, иначе {@code null}
     * @see UsersEntity#login
     * @see UsersEntity#password
     */
    UsersEntity getUserByLoginAndPassword(String login, String password);
}
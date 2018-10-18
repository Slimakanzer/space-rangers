package com.spaceRangers.service;

import com.spaceRangers.entities.UserAccountEntity;
import com.spaceRangers.entities.UsersEntity;

public interface RegistrationService {

    /**
     * Логгирование юзера
     * @param login
     * @param password
     * @return
     */
    UsersEntity loginUser(String login, String password);

    /**
     * Регистрирование пользователя
     * @param login
     * @param password
     * @return
     */
    UsersEntity createUser(String login, String password);

    UserAccountEntity getUserAccount(String login);
}

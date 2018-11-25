package com.spaceRangers.service;

import com.spaceRangers.entities.GroupAuthorityEntity;
import com.spaceRangers.entities.UserAccountEntity;
import com.spaceRangers.entities.UsersEntity;
import org.springframework.security.core.userdetails.User;

import java.util.List;

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
     * @return
     */
    UsersEntity createUser(UserAccountEntity user);

    /**
     * Получение аккаунта пользователя по login
     * @param login
     * @return
     */
    UserAccountEntity getUserAccount(String login);

    /**
     * Получение персонажа пользователя по User
     * @param user
     * @return
     */
    UsersEntity getUser(User user);

    UserAccountEntity authentification(String mail);
}

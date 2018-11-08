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

    UserAccountEntity getUserAccount(String login);

    UsersEntity getUser(User user);
}

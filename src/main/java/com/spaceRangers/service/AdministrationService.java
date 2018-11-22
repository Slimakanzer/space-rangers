package com.spaceRangers.service;

import com.spaceRangers.entities.*;

import java.util.List;

public interface AdministrationService {



    /**
     * Получение состояния пользователя
     * состояние - это бан, кик
     * @param idUser
     * @return
     */
    StateUserEntity getStateUserByIdUser(int idUser);

    /**
     * Удаление пользователя из системы
     * @return
     */
    boolean dropUser(UsersEntity userAccount);

    /**
     * Обновление пользователя (например, id состояния поменять)
     * @param user
     * @return
     */
    UsersEntity updateUser(UsersEntity user);
}

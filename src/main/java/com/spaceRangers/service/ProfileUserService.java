package com.spaceRangers.service;

import com.spaceRangers.entities.UsersEntity;

import java.util.List;

public interface ProfileUserService {

    /**
     * Обновление пользовтеля
     * @param user
     * @return
     */
    UsersEntity updateUser(UsersEntity user);

    /**
     * Получение профиля игрока
     * @param idUser
     * @return
     */
    UsersEntity getUser(int idUser);


    /**
     * Получение id пользователя по его логину
     * @param userLogin
     * @return
     */
    UsersEntity getUser(String userLogin);

    /**
     * Получение списка игроков по уровню
     * @return
     */

    List<UsersEntity> getListUsers();
}

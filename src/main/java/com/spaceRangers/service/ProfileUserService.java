package com.spaceRangers.service;

import com.spaceRangers.entities.UsersEntity;

import java.util.List;

public interface ProfileUserService {

    /**
     * Обновление пользовтеля
     * @param user
     * @return
     */
    boolean updateUser(UsersEntity user);

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
    // TODO вынести запрос в Repository
    UsersEntity getUser(String userLogin);

    /**
     * Получение списка игроков по уровню
     * @param level
     * @return
     */
    List<UsersEntity> getUserListByLevel(int level);

    List<UsersEntity> getListUsers();
}

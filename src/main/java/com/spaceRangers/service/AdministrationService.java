package com.spaceRangers.service;

import com.spaceRangers.entities.*;

import java.util.List;

public interface AdministrationService {

    /**
     * Получение списка групп пользователя
     * @param user
     * @return
     */
    List<UserGroupEntity> getUsersGroups(UsersEntity user);

    /**
     * Добавление пользователя в группу
     * @param userGroup
     * @return
     */
    UserGroupEntity createUserGroup(UserGroupEntity userGroup);

    /**
     * Удаление пользователя из группы
     * @param userGroup
     * @return
     */
    boolean dropUserGroup(UserGroupEntity userGroup);

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

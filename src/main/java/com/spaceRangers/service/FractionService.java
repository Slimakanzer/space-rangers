package com.spaceRangers.service;

import com.spaceRangers.entities.*;

import java.util.List;
import java.util.Map;

public interface FractionService {

    /**
     * Отправка заявки во фракцию
     * @param userFraction
     * @return
     */
    boolean createUserFraction(UserFractionEntity userFraction);

    /**
     *
     * Получение списка фракций в системе
     * @return
     */
    List<FractionEntity> getListFractions();

    /**
     * Получение фракции по id
     * @param idFraction
     * @return
     */
    FractionEntity getFraction(int idFraction);

    /**
     * Получение фракции по имени
     * @param name
     * @return
     */
    FractionEntity getFraction(String name);

    /**
     * Получение списка пользователей, которые находятся в данной фракции
     * @param idFraction
     * @return
     */
    List<UsersEntity> getListUsersInFraction(int idFraction);

    /**
     * Получение списка пользователей, которые находятся в данной фракции
     * с определенным их состоянием
     * @param idFraction
     * @return
     */
    List<UsersEntity> getListUsersInFractionAndUserState(int idFraction, int idStateUserFraction);

    /**
     * Получение типа задания по id
     * @param idTypeTask
     * @return
     */
    TypeTaskEntity getTypeTask(int idTypeTask);

    /**
     * Получение типа задания по name
     * @return
     */
    TypeTaskEntity getTypeTask(String name);

    /**
     * Получение состояния задания по id
     * @param id
     * @return
     */
    StateTaskEntity getStateTask(int id);

    /**
     * Получение состояния задания по имени
     * @param name
     * @return
     */
    StateTaskEntity getStateTask(String name);

    /**
     * Получение состояния приватности задания по id
     * @param id
     * @return
     */
    StatePrivacyEntity getStatePrivacy(int id);

    /**
     * Получение состояния приватности задания по имени
     * @return
     */
    StatePrivacyEntity getStatePrivacy(String name);

    /**
     * Получение задач, поставленных фракцией (Для обычных пользователей только их область видимости)
     * @param idFraction
     * @return
     */
    List<TaskEntity> getFractionTasks(int idFraction);
}

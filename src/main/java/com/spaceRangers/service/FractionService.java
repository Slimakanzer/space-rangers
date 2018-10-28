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
    UserFractionEntity createUserFraction(UserFractionEntity userFraction);

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
     * Получение задач, поставленных фракцией (Для обычных пользователей только их область видимости)
     * @param idFraction
     * @return
     */
    List<TaskEntity> getFractionTasks(int idFraction);
}

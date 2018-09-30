package com.spaceRangers.service;

import com.spaceRangers.entities.FractionEntity;
import com.spaceRangers.entities.PoliticsEntity;
import com.spaceRangers.entities.TaskEntity;
import com.spaceRangers.entities.UsersEntity;

import java.util.List;

/**
 * Интерфейс сервиса получения информации об фракции
 * @version 1.0
 * @author Ларочкин Г.И.
 */
public interface FractionInformationService {

    /**
     * Получение списка пользователей в определенной фракции
     * @param fraction {@link FractionEntity сущность фракции}, из которой берется список
     * @return возвращается список {@link UsersEntity объектов пользователей}
     * @see FractionEntity
     * @see UsersEntity
     */
    List<UsersEntity> getUsersInFraction(FractionEntity fraction);

    /**
     * Получение списка задач фракции
     * @param fraction {@link FractionEntity сущность фракции}, задачи которой необходимо получить
     * @return возвращается список {@link TaskEntity задач фракции}
     * @see FractionEntity
     * @see TaskEntity
     */
    List<TaskEntity> getTasksInFraction(FractionEntity fraction);

    /**
     * Получение списка фракций в системе
     * @return возвращается список {@link FractionEntity фракций}
     * @see FractionEntity
     */
    List<FractionEntity> getFractions();

    /**
     * Получение политической системы фракции
     * @param fraction {@link FractionEntity сущность фракции}, политическую систему которой необходимо получить
     * @return возвращается {@link PoliticsEntity сущность политической системы}
     * @see FractionEntity
     * @see PoliticsEntity
     */
    PoliticsEntity getPoliticsFraction(FractionEntity fraction);
}

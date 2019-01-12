package com.spaceRangers.service;

import com.spaceRangers.entities.*;
import javassist.NotFoundException;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

public interface FractionService {

    /**
     * Отправка заявки во фракцию
     * @param userFraction
     * @return
     */
    UserFractionEntity createUserFraction(UserFractionEntity userFraction);

    FractionEntity createFraction(FractionEntity fraction, User user);

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
     * @return
     */
    Collection<UserFractionEntity> getListUsersInFraction(FractionEntity fractionEntity, User user);

    /**
     * Получение задач, поставленных фракцией (Для обычных пользователей только их область видимости)
     * @return
     */
    List<TaskEntity> getFractionTasks(FractionEntity fration);


    boolean canCreateTasks(FractionEntity fraction, UsersEntity user) throws NotFoundException;

    /**
     * Получение роли пользователя во фракции
     * @param fraction
     * @param user
     * @return
     */
    StateUserFractionEntity roleUserInFraction(FractionEntity fraction, User user);

    /**
     * Получение задания по id
     * @param id
     * @return
     */
    TaskEntity getFractionTask(int id);

    /**
     * Подача заявки во фракцию
     * @param fraction
     * @param user
     * @return
     */
    UserFractionEntity joinFraction(FractionEntity fraction, UsersEntity user);

    /**
     * Выход из фракции
     * @param fraction
     * @param user
     * @return
     */
    UserFractionEntity outFromFraction(FractionEntity fraction, UsersEntity user);

    void updateUser(FractionEntity fractionEntity, UserFractionEntity userFractionEntity, User user) throws NoSuchElementException;



}

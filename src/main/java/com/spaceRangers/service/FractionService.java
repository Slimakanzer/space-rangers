package com.spaceRangers.service;

import com.spaceRangers.entities.*;
import javassist.NotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Map;

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
     * @param idFraction
     * @return
     */
    List<UsersEntity> getListUsersInFraction(int idFraction);

    /**
     * Получение задач, поставленных фракцией (Для обычных пользователей только их область видимости)
     * @return
     */
    List<TaskEntity> getFractionTasks(FractionEntity fration);


    boolean canCreateTasks(FractionEntity fraction, UsersEntity user) throws NotFoundException;

    StateUserFractionEntity roleUserInFraction(FractionEntity fraction, User user);


    TaskEntity getFractionTask(int id);

    UserFractionEntity joinFraction(FractionEntity fraction, UsersEntity user);

    UserFractionEntity outFromFraction(FractionEntity fraction, UsersEntity user);



}

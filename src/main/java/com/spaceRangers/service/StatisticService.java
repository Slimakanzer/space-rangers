package com.spaceRangers.service;

import com.spaceRangers.entities.*;

import java.util.List;

public interface StatisticService {

    //Получение информации о своей статистики

    /**
     * ПОлучение всех кораблей пользователя
     * @param idUser
     * @return
     */
    List<ShipEntity> getListShipsUsers(int idUser);

    /**
     * Получение всех битв пользователя
     * @param idUser
     * @return
     */
    List<BattleEntity> getListBattleUsers(int idUser);

    /**
     * Получение всех фракций пользователя
     * @param idUser
     * @return
     */
    List<FractionEntity> getListFractionUsers(int idUser);

    /**
     * Получение всех планет пользователя
     * @param idUser
     * @return
     */
    List<PlanetEntity> getListPlanetUsers(int idUser);

    /**
     * Получение групп пользователя
     * @param idUser
     * @return
     */
    List<GroupsEntity> getListGroupsUsers(int idUser);
}
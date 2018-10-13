package com.spaceRangers.service;

import com.spaceRangers.entities.*;

import java.util.List;

public interface StatisticService {

    //Получение информации о своей статистики
    List<ShipEntity> getListShipsUsers(int idUser);
    List<BattleEntity> getListBattleUsers(int idUser);
    List<FractionEntity> getListFractionUsers(int idUser);
    List<PlanetEntity> getListPlanetUsers(int idUser);
    List<GroupsEntity> getListGroupsUsers(int idUser);
}
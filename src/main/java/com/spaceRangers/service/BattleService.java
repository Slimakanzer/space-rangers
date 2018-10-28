package com.spaceRangers.service;

import com.spaceRangers.entities.*;
import com.spaceRangers.repository.StateUserBattleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BattleService {

    /**
     * Получение userBattle по id
     * @param idUserBattle
     * @return
     */
    StateUserBattleEntity getUserBattleById(int idUserBattle);

    /**
     * Получение по названию
     * @param
     * @return
     */
    StateUserFractionEntity getUserBattleByName(String name);
    /**
     * Создание битвы (когда начинается бой)
     * @param battle
     * @return
     */
    BattleEntity createBattle(BattleEntity battle);

    /**
     * Добавление участника в битву (может быть несколько пользователей в битве)
     * @param userBattle
     * @return
     */
    UserBattleEntity createBattleUser(UserBattleEntity userBattle);

    /**
     * Обновление состояния игрока в битве
     * Например, проиграл, вышел из битвы
     * @param userBattle
     * @return
     */
    UserBattleEntity updateBattleUser(UserBattleEntity userBattle);

    /**
     * Получение списка пользователей в битве
     * @param idBattle
     * @return
     */
    List<UsersEntity> getListUsersInBattle(int idBattle);

    /**
     * Добавление корабля в битву
     * @param shipBattle
     * @return
     */
    ShipBattleEntity createShipBattle(ShipBattleEntity shipBattle);

    /**
     * Получение списка кораблей пользователя в битве
     * @return
     */
//    List<ShipEntity> getListShipInBattle(int idBattle);
}

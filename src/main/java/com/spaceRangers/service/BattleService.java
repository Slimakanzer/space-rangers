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
     * @param login
     * @return
     */
    StateUserFractionEntity getUserBattleByName(String name);
    /**
     * Создание битвы (когда начинается бой)
     * @param battle
     * @return
     */
    boolean createBattle(BattleEntity battle);

    /**
     * Добавление участника в битву (может быть несколько пользователей в битве)
     * @param userBattle
     * @return
     */
    boolean createBattleUser(UserBattleEntity userBattle);

    /**
     * Обновление состояния игрока в битве
     * Например, проиграл, вышел из битвы
     * @param userBattle
     * @return
     */
    boolean updateBattleUser(UserBattleEntity userBattle);

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
    boolean createShipBattle(ShipBattleEntity shipBattle);

    /**
     * Получение списка кораблей пользователя в битве
     * @param idUser
     * @return
     */
    List<ShipEntity> getListShipInbattleByIdUser(int idUser, int idBattle);
}

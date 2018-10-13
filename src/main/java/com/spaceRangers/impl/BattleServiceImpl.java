package com.spaceRangers.impl;

import com.spaceRangers.entities.*;
import com.spaceRangers.repository.*;
import com.spaceRangers.service.BattleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("battleService")
public class BattleServiceImpl implements BattleService {

    @Autowired
    BattleRepository battleRepository;

    @Autowired
    UserBattleRepository userBattleRepository;

    @Autowired
    ShipBattleRepository shipBattleRepository;

    @Autowired
    ShipRepository shipRepository;
    /**
     * Получение userBattle по id
     *
     * @param idUserBattle
     * @return
     */
    @Override
    public StateUserBattleEntity getUserBattleById(int idUserBattle) {
        return null;
    }

    /**
     * Получение по названию
     *
     * @param name@return
     */
    @Override
    public StateUserFractionEntity getUserBattleByName(String name) {
        return null;
    }

    /**
     * Создание битвы (когда начинается бой)
     *
     * @param battle
     * @return
     */
    @Override
    public boolean createBattle(BattleEntity battle) {
        battleRepository.save(battle);
        return true;
    }

    /**
     * Добавление участника в битву (может быть несколько пользователей в битве)
     *
     * @param userBattle
     * @return
     */
    @Override
    public boolean createBattleUser(UserBattleEntity userBattle) {
        userBattleRepository.save(userBattle);
        return true;
    }

    /**
     * Обновление состояния игрока в битве
     * Например, проиграл, вышел из битвы
     *
     * @param userBattle
     * @return
     */
    @Override
    public boolean updateBattleUser(UserBattleEntity userBattle) {
        userBattleRepository.save(userBattle);
        return true;
    }

    /**
     * Получение списка пользователей в битве
     *
     * @param idBattle
     * @return
     */
    @Override
    public List<UsersEntity> getListUsersInBattle(int idBattle) {
        return battleRepository.getListUsersInBattle(idBattle);
    }

    /**
     * Добавление корабля в битву
     *
     * @param shipBattle
     * @return
     */
    @Override
    public boolean createShipBattle(ShipBattleEntity shipBattle) {
        shipBattleRepository.save(shipBattle);
        return true;
    }

    /**
     * Получение списка кораблей пользователя в битве
     *
     * @param idUser
     * @return
     */
    @Override
    public List<ShipEntity> getListShipInbattleByIdUser(int idUser, int idBattle) {
        return shipBattleRepository.getListShipinBattleByIdUser(idBattle, idUser);
    }
}

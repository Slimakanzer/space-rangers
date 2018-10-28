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

    private final BattleRepository battleRepository;

    private final UserBattleRepository userBattleRepository;

    private final ShipBattleRepository shipBattleRepository;

    private final ShipRepository shipRepository;

    @Autowired
    public BattleServiceImpl(BattleRepository battleRepository, UserBattleRepository userBattleRepository, ShipBattleRepository shipBattleRepository, ShipRepository shipRepository) {
        this.battleRepository = battleRepository;
        this.userBattleRepository = userBattleRepository;
        this.shipBattleRepository = shipBattleRepository;
        this.shipRepository = shipRepository;
    }

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
    public BattleEntity createBattle(BattleEntity battle) {
        battleRepository.save(battle);
        return battle;
    }

    /**
     * Добавление участника в битву (может быть несколько пользователей в битве)
     *
     * @param userBattle
     * @return
     */
    @Override
    public UserBattleEntity createBattleUser(UserBattleEntity userBattle) {
        userBattleRepository.save(userBattle);
        return userBattle;
    }

    /**
     * Обновление состояния игрока в битве
     * Например, проиграл, вышел из битвы
     *
     * @param userBattle
     * @return
     */
    @Override
    public UserBattleEntity updateBattleUser(UserBattleEntity userBattle) {
        userBattleRepository.save(userBattle);
        return userBattle;
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
    public ShipBattleEntity createShipBattle(ShipBattleEntity shipBattle) {
        shipBattleRepository.save(shipBattle);
        return shipBattle;
    }

    /**
     * Получение списка кораблей пользователя в битве
     *
     * @param
     * @return
     */
//    @Override
//    public List<ShipEntity> getListShipInBattle(int idBattle) {
//        return shipBattleRepository.findShipsInBattle(idBattle);
//    }
}

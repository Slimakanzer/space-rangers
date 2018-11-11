package com.spaceRangers.impl;

import com.spaceRangers.entities.*;
import com.spaceRangers.repository.*;
import com.spaceRangers.service.BattleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
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


    @Override
    public StateUserBattleEntity getUserBattleById(int idUserBattle) {
        return null;
    }


    @Override
    public StateUserFractionEntity getUserBattleByName(String name) {
        return null;
    }

    @Override
    public BattleEntity createBattle(BattleEntity battle) {
        battleRepository.save(battle);
        return battle;
    }

    @Override
    public UserBattleEntity createBattleUser(UserBattleEntity userBattle) {
        userBattleRepository.save(userBattle);
        return userBattle;
    }

    @Override
    public UserBattleEntity updateBattleUser(UserBattleEntity userBattle) {
        userBattleRepository.save(userBattle);
        return userBattle;
    }

    @Override
    public List<UsersEntity> getListUsersInBattle(int idBattle) {
        return battleRepository.getListUsersInBattle(idBattle);
    }

    @Override
    public ShipBattleEntity createShipBattle(ShipBattleEntity shipBattle) {
        shipBattleRepository.save(shipBattle);
        return shipBattle;
    }

    public Collection<BattleEntity> getBattlesUser(UsersEntity usersEntity){
        Collection<BattleEntity> list = new ArrayList<>();
        usersEntity.getUsersBattle()
                .stream()
                .forEach(e-> {
                    BattleEntity battleEntity = e.getBattle();
                    list.add(battleEntity);
                });

        return list;
    }

    public BattleEntity getBattle(int id){
        return battleRepository.findById(id).get();
    }
}

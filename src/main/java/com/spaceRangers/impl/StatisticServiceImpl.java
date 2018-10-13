package com.spaceRangers.impl;

import com.spaceRangers.entities.*;
import com.spaceRangers.repository.*;
import com.spaceRangers.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("statisticService")
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    ShipRepository shipRepository;

    @Autowired
    BattleRepository battleRepository;

    @Autowired
    FractionRepository fractionRepository;

    @Autowired
    PlanetRepository planetRepository;

    @Autowired
    GroupsRepository groupsRepository;


    @Override
    public List<ShipEntity> getListShipsUsers(int idUser) {
        return shipRepository.getListShipsUser(idUser);
    }

    @Override
    public List<BattleEntity> getListBattleUsers(int idUser) {
        return battleRepository.getListBattleUser(idUser);
    }

    @Override
    public List<FractionEntity> getListFractionUsers(int idUser) {
        return fractionRepository.getListFractionsUser(idUser);
    }

    @Override
    public List<PlanetEntity> getListPlanetUsers(int idUser) {
        return planetRepository.getListPlanetUser(idUser);
    }

    @Override
    public List<GroupsEntity> getListGroupsUsers(int idUser) {
        return groupsRepository.getListGroupsUser(idUser);
    }
}

package com.spaceRangers.impl;

import com.spaceRangers.entities.*;
import com.spaceRangers.repository.*;
import com.spaceRangers.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("statisticService")
public class StatisticServiceImpl implements StatisticService {

    private final ShipRepository shipRepository;

    private final BattleRepository battleRepository;

    private final FractionRepository fractionRepository;

    private final PlanetRepository planetRepository;

    private final GroupsRepository groupsRepository;

    private final UserRepository userRepository;

    @Autowired
    public StatisticServiceImpl(ShipRepository shipRepository, BattleRepository battleRepository, FractionRepository fractionRepository, PlanetRepository planetRepository, GroupsRepository groupsRepository, UserRepository userRepository) {
        this.shipRepository = shipRepository;
        this.battleRepository = battleRepository;
        this.fractionRepository = fractionRepository;
        this.planetRepository = planetRepository;
        this.groupsRepository = groupsRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<ShipEntity> getListShipsUsers(int idUser) {
        return shipRepository.findShipEntitiesByUsersByIdUser(userRepository.findById(idUser).get());
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
        return planetRepository.findPlanetEntitiesByUsersByIdUser(userRepository.findById(idUser).get());
    }

    @Override
    public List<GroupsEntity> getListGroupsUsers(int idUser) {
        return groupsRepository.getListGroupsUser(idUser);
    }
}

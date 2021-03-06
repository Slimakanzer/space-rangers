package com.spaceRangers.impl;

import com.spaceRangers.entities.*;
import com.spaceRangers.repository.TaskRepository;
import com.spaceRangers.service.FractionService;
import com.spaceRangers.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilterService {

    @Autowired
    FractionService fractionService;

    @Autowired
    RegistrationService registrationService;


    public boolean taskFilter(FractionEntity fraction, TaskEntity task, User user){

        if(taskInFraction(fraction, task)) {

            if (task == null) return false;

            UsersEntity usersEntity = registrationService.getUser(user);

            List<UserFractionEntity> entityList = new ArrayList<>();
            UserFractionEntity userFractionEntity;
            if (entityList.size() == 0) {
                userFractionEntity = null;
            } else userFractionEntity = entityList.get(0);
            String statePrivacy = task.getStatePrivacy().getName();

            switch (statePrivacy) {
                case "private": {
                    if (userFractionEntity == null) return false;
                    if (userFractionEntity.getStateUserFraction().getName().equals("leader")) return true;
                    else return false;
                }
                case "protected": {
                    if (userFractionEntity == null) return false;
                    if (userFractionEntity.getStateUserFraction().getName().equals("player")) return true;
                    else return false;
                }
                case "public": {
                    return true;
                }
                default: {
                    return false;
                }
            }
        }else return false;
    }

    public boolean taskInFraction(FractionEntity fraction, TaskEntity task){
        if (fraction==null) return false;
        if(task==null) return false;

        long result = fraction
                .getTasks()
                .stream()
                .filter(
                        e->e.getId().equals(task.getId())
                )
                .count();

        if(result == 0)return false;
        else return true;
    }


    public boolean userInFraction(FractionEntity fraction, User user){
        UsersEntity usersEntity = registrationService.getUser(user);
        List<UserFractionEntity> list= new ArrayList<>();
        if(list.size() == 0) return false;
        UserFractionEntity userFractionEntity = list.get(0);
        String stateName = userFractionEntity.getStateUserFraction().getName();

        switch (stateName){
            case "leader": return true;
            case "player": return true;
            case "adviser": return true;
            default: return false;
        }
    }


    public boolean isThisUser(UsersEntity usersEntity, User user){
        if(usersEntity == null) return false;

        if(registrationService.getUser(user).getId().equals(usersEntity.getId())) return true;
        return false;
    }

    public boolean usersBase(UsersEntity usersEntity, BaseEntity baseEntity){
        if(usersEntity == null || baseEntity == null) return false;

        List bases = usersEntity.getBases()
                .stream()
                .filter(e->e.getId().equals(baseEntity.getId()))
                .collect(Collectors.toList());

        if(bases.size()==0) return false;
        return true;
    }

    public boolean isUsersShip(UsersEntity usersEntity, ShipEntity shipEntity){
        List<ShipEntity> list = usersEntity.getShips()
                .stream()
                .filter(e->e.getId().equals(shipEntity.getId()))
                .collect(Collectors.toList());

        if (list.size()==0) return false;
        return true;

    }

    public boolean isPlanetInSystem(SystemEntity systemEntity, PlanetEntity planetEntity){
        long count = systemEntity.getPlanets()
                .stream()
                .filter(e->e.getId().equals(planetEntity.getId()))
                .count();

        if(count == 0) return false; else return true;
    }

    public boolean isResourceInPlanet(PlanetEntity planetEntity, ResourceEntity resourceEntity){
        long count = planetEntity.getResources()
                .stream()
                .filter(e->e.getId().equals(resourceEntity.getId()))
                .count();

        if(count == 0) return false; else return true;
    }

    public boolean isUsersPlanet(User user, PlanetEntity planetEntity){
        if (planetEntity==null) return false;
        UsersEntity usersEntity = registrationService.getUser(user);
        long count = usersEntity.getPlanets()
                .stream()
                .filter(e->e.getId().equals(planetEntity.getId()))
                .count();

        if(count==0) return false; else return true;
    }

    public boolean isUsersBattle(User user, BattleEntity battleEntity){
        if (battleEntity == null) return false;
        UsersEntity usersEntity = registrationService.getUser(user);
        long count = usersEntity.getUsersBattle()
                .stream()
                .filter(e->{
                    if(e.getBattle().getId().equals(battleEntity.getId())) return true;
                    else return false;
                }).count();
        if(count == 0) return false; else return true;
    }
}

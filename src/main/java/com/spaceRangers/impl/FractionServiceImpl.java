package com.spaceRangers.impl;

import com.spaceRangers.config.websocket.exceptions.NotEnoughMoneyException;
import com.spaceRangers.config.websocket.exceptions.TypeError;
import com.spaceRangers.entities.*;
import com.spaceRangers.repository.*;
import com.spaceRangers.service.FractionService;
import com.spaceRangers.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

@Service("fractionService")
public class FractionServiceImpl implements FractionService {

    final UserFractionRepository userFractionRepository;

    private final FractionRepository fractionRepository;

    private final UserRepository userRepository;

    public final StateUserFractionRepository stateUserFractionRepository;

    @Autowired
    GroupsRepository groupsRepository;
    @Autowired
    PoliticsRepository politicsRepository;

    final
    TaskRepository taskRepository;

    final
    RegistrationService registrationService;



    @Autowired
    public FractionServiceImpl(UserFractionRepository userFractionRepository, FractionRepository fractionRepository, UserRepository userRepository, StateUserFractionRepository stateUserFractionRepository, TaskRepository taskRepository, RegistrationService registrationService) {
        this.userFractionRepository = userFractionRepository;
        this.fractionRepository = fractionRepository;
        this.userRepository = userRepository;
        this.stateUserFractionRepository = stateUserFractionRepository;
        this.taskRepository = taskRepository;
        this.registrationService = registrationService;
    }

    @Override
    public UserFractionEntity createUserFraction(UserFractionEntity userFraction) {
        userFractionRepository.save(userFraction);
        return userFraction;
    }

    @Override
    public List<FractionEntity> getListFractions() {

        return fractionRepository
                .findAll();
    }

    @Override
    public FractionEntity getFraction(int idFraction) {

        return fractionRepository.findById(idFraction).get();
    }

    @Override
    public FractionEntity getFraction(String name) {
        return fractionRepository.getFractionByNameFraction(name);
    }

    @Override
    public Collection<UserFractionEntity> getListUsersInFraction(FractionEntity fractionEntity, UsersEntity user) {

        Collection<UserFractionEntity> userFractionEntities = fractionEntity.getUsersFraction();

        StateUserFractionEntity role = roleUserInFraction(fractionEntity, user);
        if( role == null || role.getName().equals("candidate") || role.getName().equals("player") || role.getName().equals("left")){
            userFractionEntities = userFractionEntities.stream().filter(e->{
                String nameState = e.getStateUserFraction().getName();

                if(nameState.equals("leader")) return true;
                if(nameState.equals("player")) return true;
                return false;
            })
                    .collect(Collectors.toList());
        }

        return userFractionEntities;
    }

    @Override
    public List<TaskEntity> getFractionTasks(FractionEntity fration) {
        return fration.getTasks()
                .stream()
                .filter(e->e.getStatePrivacy().getName().equals("public"))
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public FractionEntity createFraction(FractionEntity fraction, UsersEntity user) throws NotEnoughMoneyException {

        if (user.getCoins() < 10000) throw new NotEnoughMoneyException("You dont have money", TypeError.NOT_ENOUGH_MONEY_CREATE_FRACTION);
        user.setCoins(user.getCoins()- 10000);
        userRepository.save(user);
        fraction.setId(null);
        fraction.setCountPlayer(1);
        fraction.setPolitics(politicsRepository.findById(1).get());
        fractionRepository.save(fraction);

        UserFractionEntity userFractionEntity = new UserFractionEntity();
        userFractionEntity.setIdUser(user.getId());
        userFractionEntity.setUser(user);
        userFractionEntity.setFraction(fraction);
        userFractionEntity.setDate(new Date(new java.util.Date().getTime()));

        StateUserFractionEntity stateUserFraction = stateUserFractionRepository.findStateUserFractionEntityByName("leader");

        userFractionEntity.setStateUserFraction(stateUserFraction);
        userFractionRepository.save(userFractionEntity);
        return fraction;
    }

    @Override
    public boolean canCreateTasks(FractionEntity fraction, UsersEntity user) {

        return false;
    }

    @Override
    public StateUserFractionEntity roleUserInFraction(FractionEntity fraction, UsersEntity user) {
        if (fraction.getUsersFraction().contains(user.getUserFraction()))
            return user.getUserFraction().getStateUserFraction();
        return null;
    }

    @Override
    @Transactional
    public TaskEntity getFractionTask(int id) {
        TaskEntity taskEntity = taskRepository.findById(id).get();
        return taskEntity;

    }

    @Override
    @Transactional
    public UserFractionEntity joinFraction(FractionEntity fraction, UsersEntity user){
        UserFractionEntity userFractionEntity = new UserFractionEntity();
        userFractionEntity.setIdUser(user.getId());
        userFractionEntity.setUser(user);
        userFractionEntity.setFraction(fraction);
        userFractionEntity.setStateUserFraction(stateUserFractionRepository.findStateUserFractionEntityByName("candidate"));
        userFractionEntity.setDate(new Date(new java.util.Date().getTime()));
        userFractionRepository.save(userFractionEntity);
        return userFractionEntity;

    }

    @Override
    @Transactional
    public boolean outFromFraction(FractionEntity fraction, UsersEntity user){
        boolean result = fraction.getUsersFraction().remove(user.getUserFraction());
        fraction.setCountPlayer(fraction.getCountPlayer()-1);
        fractionRepository.save(fraction);
        userFractionRepository.delete(user.getUserFraction());
        return result;
    }

    @Transactional
    public void updateUser(UserFractionEntity userFractionEntity) throws NoSuchElementException{
        userFractionRepository.save(userFractionEntity);
    }
}

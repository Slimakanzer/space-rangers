package com.spaceRangers.impl;

import com.spaceRangers.entities.*;
import com.spaceRangers.repository.*;
import com.spaceRangers.service.FractionService;
import com.spaceRangers.service.RegistrationService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("fractionService")
public class FractionServiceImpl implements FractionService {

    final UserFractionRepository userFractionRepository;

    private final FractionRepository fractionRepository;

    private final UserRepository userRepository;

    public final StateUserFractionRepository stateUserFractionRepository;

    @Autowired
    GroupsRepository groupsRepository;

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
    public List<UsersEntity> getListUsersInFraction(int idFraction) {
        List<UsersEntity> users = new ArrayList<>();

        userFractionRepository
                .findAll()
                .stream()
                .forEach(e->{
                    if(e.getIdFraction() == idFraction) users.add(
                            userRepository.findById(e.getIdUser()).get()
                    );
                });
        return users;
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
    public FractionEntity createFraction(FractionEntity fraction, User user) {
        UserAccountEntity userAccountEntity = registrationService.getUserAccount(user.getUsername());
        fraction.setId(null);

        fractionRepository.save(fraction);
        userAccountEntity.getGroups().add(groupsRepository.findGroupsEntityByName("leader"));

        UserFractionEntity userFractionEntity = new UserFractionEntity();
        userFractionEntity.setIdUser(userAccountEntity.getId());
        userFractionEntity.setUser(userAccountEntity.getUser());
        userAccountEntity.getUser().getUsersFraction().add(userFractionEntity);
        userFractionEntity.setFraction(fraction);
        userFractionEntity.setIdFraction(fraction.getId());
        fraction.getUsersFraction().add(userFractionEntity);

        userFractionEntity.setDate(new Date(new java.util.Date().getTime()));

        StateUserFractionEntity stateUserFraction = stateUserFractionRepository.findStateUserFractionEntityByName("leader");

        userFractionEntity.setStateUserFraction(stateUserFraction);
        userFractionRepository.save(userFractionEntity);

        return fraction;
    }

    @Override
    public boolean canCreateTasks(FractionEntity fraction, UsersEntity user) {
        List<UserFractionEntity> list = user.getUsersFraction()
                .stream()
                .filter(e-> e.getIdFraction().equals(fraction.getId()))
                .collect(Collectors.toList());


        if(list.get(0).getStateUserFraction().getName().equals("owner")) return true;

        return false;
    }

    @Override
    public StateUserFractionEntity roleUserInFraction(FractionEntity fraction, User user){
        UsersEntity users = registrationService.getUser(user);
        List<UserFractionEntity> userFraction = users.getUsersFraction().stream()
                .filter(e->e.getIdFraction().equals(fraction.getId()))
                .collect(Collectors.toList());


        if(userFraction.size() == 0) return null;

        StateUserFractionEntity  state = userFraction.get(0).getStateUserFraction();
        if(state.getName().equals("owner") || state.getName().equals("adviser") || state.getName().equals("player")) return state;

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
        user.getUsersFraction().add(userFractionEntity);
        userFractionEntity.setIdFraction(fraction.getId());
        userFractionEntity.setFraction(fraction);
        fraction.getUsersFraction().add(userFractionEntity);

        userFractionEntity.setStateUserFraction(stateUserFractionRepository.findStateUserFractionEntityByName("candidate"));
        userFractionEntity.setDate(new Date(new java.util.Date().getTime()));

        userFractionRepository.save(userFractionEntity);
        return userFractionEntity;

    }

    @Override
    @Transactional
    public UserFractionEntity outFromFraction(FractionEntity fraction, UsersEntity user){
        UserFractionEntity userFractionEntity = user.getUsersFraction()
                .stream()
                .filter(e->e.getIdFraction().equals(fraction.getId()))
                .findAny().get();

        if(userFractionEntity==null) return null;
        userFractionEntity.setStateUserFraction(stateUserFractionRepository.findStateUserFractionEntityByName("leaved"));
        userFractionRepository.save(userFractionEntity);

        return userFractionEntity;
    }
}

package com.spaceRangers.impl;

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
    public Collection<UserFractionEntity> getListUsersInFraction(FractionEntity fractionEntity, User user) {

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
        return state;

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
        userFractionEntity.setStateUserFraction(stateUserFractionRepository.findStateUserFractionEntityByName("left"));
        userFractionRepository.save(userFractionEntity);

        return userFractionEntity;
    }

    @Transactional
    public void updateUser(FractionEntity fractionEntity, UserFractionEntity userFractionEntity, User user) throws NoSuchElementException{

        UserFractionEntityPK userFractionEntityPK = new UserFractionEntityPK();
        userFractionEntityPK.setIdFraction(userFractionEntity.getIdFraction());
        userFractionEntityPK.setIdUser(userFractionEntity.getIdUser());

        Optional<UserFractionEntity> userFractionEntityOptional = userFractionRepository.findById(userFractionEntityPK);

        if(userFractionEntityOptional.isPresent()){
            UserFractionEntity userFractionEntity1 = userFractionEntityOptional.get();
            userFractionEntity1.setStateUserFraction(userFractionEntity.getStateUserFraction());
            userFractionRepository.save(userFractionEntity1);
        }else throw new NoSuchElementException("Not found");
    }
}

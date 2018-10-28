package com.spaceRangers.impl;

import com.spaceRangers.entities.*;
import com.spaceRangers.repository.FractionRepository;
import com.spaceRangers.repository.UserFractionRepository;
import com.spaceRangers.repository.UserRepository;
import com.spaceRangers.service.FractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("fractionService")
public class FractionServiceImpl implements FractionService {

    final UserFractionRepository userFractionRepository;

    private final FractionRepository fractionRepository;

    private final UserRepository userRepository;

    @Autowired
    public FractionServiceImpl(UserFractionRepository userFractionRepository, FractionRepository fractionRepository, UserRepository userRepository) {
        this.userFractionRepository = userFractionRepository;
        this.fractionRepository = fractionRepository;
        this.userRepository = userRepository;
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
    public List<TaskEntity> getFractionTasks(int idFraction) {
        // TODO сделать реализацию
        return null;
    }
}

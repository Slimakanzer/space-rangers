package com.spaceRangers.impl;

import com.spaceRangers.entities.*;
import com.spaceRangers.repository.FractionRepository;
import com.spaceRangers.repository.UserFractionRepository;
import com.spaceRangers.repository.UserRepository;
import com.spaceRangers.service.FractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("fractionService")
public class FractionServiceImpl implements FractionService {
    @Autowired
    private UserFractionRepository userFractionRepository;

    @Autowired
    private FractionRepository fractionRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean createUserFraction(UserFractionEntity userFraction) {
        userFractionRepository.save(userFraction);
        return true;
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
        // TODO вынести в repository
        return null;
    }

    /**
     * Получение списка пользователей, которые находятся в данной фракции
     * с определенным их состоянием
     *
     * @param idFraction
     * @param idStateUserFraction
     * @return
     */
    @Override
    public List<UsersEntity> getListUsersInFractionAndUserState(int idFraction, int idStateUserFraction) {
        // TODO в репозит
        return null;
    }

    @Override
    public List<TaskEntity> getFractionTasks(int idFraction) {
        // TODO в репозит
        return null;
    }

    /**
     * Получение типа задания по id
     *
     * @param idTypeTask
     * @return
     */
    @Override
    public TypeTaskEntity getTypeTask(int idTypeTask) {
        return null;
    }

    /**
     * Получение типа задания по name
     *
     * @param name
     * @return
     */
    @Override
    public TypeTaskEntity getTypeTask(String name) {
        return null;
    }

    /**
     * Получение состояния задания по id
     *
     * @param id
     * @return
     */
    @Override
    public StateTaskEntity getStateTask(int id) {
        return null;
    }

    /**
     * Получение состояния задания по имени
     *
     * @param name
     * @return
     */
    @Override
    public StateTaskEntity getStateTask(String name) {
        return null;
    }

    /**
     * Получение состояния приватности задания по id
     *
     * @param id
     * @return
     */
    @Override
    public StatePrivacyEntity getStatePrivacy(int id) {
        return null;
    }

    /**
     * Получение состояния приватности задания по имени
     *
     * @param name
     * @return
     */
    @Override
    public StatePrivacyEntity getStatePrivacy(String name) {
        return null;
    }
}

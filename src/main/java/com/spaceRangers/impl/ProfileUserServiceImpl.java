package com.spaceRangers.impl;

import com.spaceRangers.entities.UsersEntity;
import com.spaceRangers.repository.UserRepository;
import com.spaceRangers.service.ProfileUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("profileUserService")
public class ProfileUserServiceImpl implements ProfileUserService {

    private final UserRepository userRepository;

    @Autowired
    public ProfileUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UsersEntity updateUser(UsersEntity user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public UsersEntity getUser(int idUser) {

        return userRepository.findById(idUser).get();
    }

    @Override
    public UsersEntity getUser(String userLogin) {
        return userRepository.findUsersEntityByLogin(userLogin);
    }

    @Override
    public List<UsersEntity> getListUsers() {
        List<UsersEntity> list = new ArrayList<>();
        userRepository.findAll().forEach(e->list.add(e));
        return list;
    }
}

package com.spaceRangers.impl;

import com.spaceRangers.entities.UsersEntity;
import com.spaceRangers.repository.UserRepository;
import com.spaceRangers.service.ProfileUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("profileUserService")
public class ProfileUserServiceImpl implements ProfileUserService {

    private final UserRepository userRepository;

    @Autowired
    public ProfileUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Обновление пользовтеля
     *
     * @param user
     * @return
     */
    @Override
    @Transactional
    public UsersEntity updateUser(UsersEntity user) {
        userRepository.save(user);
        return user;
    }

    /**
     * Получение профиля игрока
     *
     * @param idUser
     * @return
     */
    @Override
    public UsersEntity getUser(int idUser) {

        return userRepository.findById(idUser).get();
    }

    /**
     * Получение id пользователя по его логину
     *
     * @param userLogin
     * @return
     */
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

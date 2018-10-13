package com.spaceRangers.impl;

import com.spaceRangers.entities.UsersEntity;
import com.spaceRangers.repository.UserRepository;
import com.spaceRangers.service.ProfileUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("profileUserService")
public class ProfileUserServiceImpl implements ProfileUserService {

    @Autowired
    UserRepository userRepository;

    /**
     * Обновление пользовтеля
     *
     * @param user
     * @return
     */
    @Override
    public boolean updateUser(UsersEntity user) {
        userRepository.save(user);
        return true;
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

    /**
     * Получение списка игроков по уровню
     *
     * @param level
     * @return
     */
    @Override
    public List<UsersEntity> getUserListByLevel(int level) {

        return userRepository.findUsersByLevel(level);
    }

    @Override
    public List<UsersEntity> getListUsers() {
        return userRepository.findAll();
    }
}

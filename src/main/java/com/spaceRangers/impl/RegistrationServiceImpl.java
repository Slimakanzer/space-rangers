package com.spaceRangers.impl;

import com.spaceRangers.entities.UserAccountEntity;
import com.spaceRangers.entities.UsersEntity;
import com.spaceRangers.repository.UserAccountRepository;
import com.spaceRangers.repository.UserRepository;
import com.spaceRangers.service.RegistrationService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("registrationService")
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserAccountRepository userAccountRepository;

    @Transactional
    public UsersEntity loginUser(String login, String password) {
        UserAccountEntity user = userAccountRepository.findUserAccountEntityByLogin(login);
            if (user.getPassword().equals(password)) {
                return userRepository.findById(
                        user.getId()
                ).get();
            } else return null;
    }

    @Transactional
    public UserAccountEntity getUserAccount(String login){
        UserAccountEntity userAccountEntity = userAccountRepository.findUserAccountEntityByLogin(login);
        return userAccountEntity;

    }

    @Transactional
    public UsersEntity createUser(String login, String password) {
        UserAccountEntity userAccount = new UserAccountEntity();
        userAccount.setLogin(login);
        userAccount.setPassword(password);
        userAccount.setId(null);

        userAccountRepository.save(userAccount);

        return userRepository.findById(
                userAccount.getId()
        ).get();
    }
}

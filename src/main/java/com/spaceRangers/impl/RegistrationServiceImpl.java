package com.spaceRangers.impl;

import com.spaceRangers.entities.GroupAuthorityEntity;
import com.spaceRangers.entities.GroupsEntity;
import com.spaceRangers.entities.UserAccountEntity;
import com.spaceRangers.entities.UsersEntity;
import com.spaceRangers.repository.GroupAuthorityRepository;
import com.spaceRangers.repository.GroupsRepository;
import com.spaceRangers.repository.UserAccountRepository;
import com.spaceRangers.repository.UserRepository;
import com.spaceRangers.service.RegistrationService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("registrationService")
public class RegistrationServiceImpl implements RegistrationService {

    private final UserRepository userRepository;

    private final UserAccountRepository userAccountRepository;

    private final GroupsRepository groupsRepository;


    @Autowired
    public PasswordEncoder passwordEncoder;


    @Autowired
    public RegistrationServiceImpl(UserRepository userRepository, UserAccountRepository userAccountRepository, GroupsRepository groupsRepository) {
        this.userRepository = userRepository;
        this.userAccountRepository = userAccountRepository;
        this.groupsRepository=groupsRepository;
    }

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
    public UsersEntity createUser(UserAccountEntity user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.getGroups().add(groupsRepository.findGroupsEntityByName("User"));
        user.setId(null);
        userAccountRepository.save(user);

//
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setId(user.getId());
        usersEntity.setUserAccount(user);
        usersEntity.setLevel(1);

        userRepository.save(usersEntity);
//

//        return userRepository.findById(
//                user.getId()
//        ).get();
        return null;
    }

    @Override
    @Transactional
    public UsersEntity getUser(User user) {
        return getUserAccount(user.getUsername()).getUser();
    }
}

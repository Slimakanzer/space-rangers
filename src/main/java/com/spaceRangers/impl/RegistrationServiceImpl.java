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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public UserAccountEntity getUserAccount(String login){
        UserAccountEntity userAccountEntity = userAccountRepository.findUserAccountEntityByLogin(login).get();
        return userAccountEntity;

    }

    @Transactional
    public UsersEntity createUser(UserAccountEntity user) {

        user.getGroups().add(groupsRepository.findGroupsEntityByName("User"));
        user.setId(null);
        userAccountRepository.save(user);

//
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setId(user.getId());
        usersEntity.setUserAccount(user);
        usersEntity.setLevel(1);
        usersEntity.setFirstName("Username");
        usersEntity.setLastName("Username");
        usersEntity.setCoins(2000);
        usersEntity.setLogin(user.getLogin());
        userRepository.save(usersEntity);
        return usersEntity;
    }

    @Override
    public UsersEntity getUser(User user) {
        return getUserAccount(user.getUsername()).getUser();
    }


    public UserAccountEntity authentification(String mail){

        Optional<UserAccountEntity> user = userAccountRepository.findUserAccountEntityByMail(mail);

        if(user.isPresent()){
            return user.get();
        }else {
            UserAccountEntity userAccount = new UserAccountEntity();
            userAccount.setMail(mail);
            userAccount.setLogin(mail);
            userAccount.setPassword("");
            this.createUser(userAccount);
            return userAccount;
        }
    }


    public UserAccountEntity registration(String login, String password) throws IllegalAccessException {
        UserAccountEntity userAccountEntity = new UserAccountEntity();
        userAccountEntity.setLogin(login);
        userAccountEntity.setPassword(passwordEncoder.encode(password));

        Optional<UserAccountEntity> user = userAccountRepository.findUserAccountEntityByLogin(login);

        if(user.isPresent()){
            throw new IllegalAccessException("Already exist");
        }

        createUser(userAccountEntity);
        return userAccountEntity;



    }
}

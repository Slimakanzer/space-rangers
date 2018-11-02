package com.spaceRangers.impl;

import com.spaceRangers.entities.*;
import com.spaceRangers.repository.*;
import com.spaceRangers.service.AdministrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("administrationService")
public class AdministrationServiceImpl implements AdministrationService {
    private final UserRepository userRepository;

    private final StateUserRepository stateUserRepository;

    private final UserAccountRepository userAccountRepository;

    @Autowired
    public AdministrationServiceImpl(UserRepository userRepository, StateUserRepository stateUserRepository, UserAccountRepository userAccountRepository) {
        this.userRepository = userRepository;
        this.stateUserRepository = stateUserRepository;
        this.userAccountRepository = userAccountRepository;
    }

//    @Override
//    public List<UserGroupEntity> getUsersGroups(UsersEntity user) {
//            return userGroupRepository.findUserGroupEntitiesByIdUser(user.getId());
//    }
//
//    @Transactional
//    @Override
//    public UserGroupEntity createUserGroup(UserGroupEntity userGroup) {
//        userGroupRepository.save(userGroup);
//        return userGroup;
//    }



    @Override
    public StateUserEntity getStateUserByIdUser(int idUser) {

        return userRepository.findById(idUser).get().getStateUser();
    }

    @Transactional
    public boolean dropUser(UsersEntity user){
        userRepository.delete(user);
        userAccountRepository.delete(
                userAccountRepository.findById(
                        user.getId()
                ).get()
        );
        return true;
    }

    @Transactional
    @Override
    public UsersEntity updateUser(UsersEntity user) {
        userRepository.save(user);
        return user;
    }

//    @Transactional
//    @Override
//    public boolean dropUserGroup(UserGroupEntity userGroup) {
//        userGroupRepository.delete(userGroup);
//        return true;
//    }

}

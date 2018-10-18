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
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserGroupRepository userGroupRepository;

    @Autowired
    private StateUserRepository stateUserRepository;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Override
    public List<UserGroupEntity> getUsersGroups(UsersEntity user) {
            return userGroupRepository.findUserGroupEntitiesByIdUser(user.getId());
    }

    @Transactional
    public void createUserGroup(UserGroupEntity userGroup) {
        userGroupRepository.save(userGroup);
    }



    @Override
    public StateUserEntity getStateUserByIdUser(int idUser) {

        return stateUserRepository.findById(
                userRepository.findById(idUser).get().getIdState()
        ).get();
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
    public boolean updateUser(UsersEntity user) {
        userRepository.save(user);
        return true;
    }

    @Transactional
    public boolean dropUserGroup(UserGroupEntity userGroup) {
        userGroupRepository.delete(userGroup);
        return true;
    }

}

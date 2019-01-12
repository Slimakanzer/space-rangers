package com.spaceRangers.service;

import com.spaceRangers.entities.ChatEntity;
import com.spaceRangers.entities.ComplainEntity;
import com.spaceRangers.entities.UsersEntity;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public interface ComplainService {

    /**
     * Создание жалобы
     * @param complainEntity
     * @return
     */
    ComplainEntity createComplain(ComplainEntity complainEntity);


    /**
     * Изменение жалобы (если исправлена или т.д.)
     * @param complainEntity
     * @return
     */
    ComplainEntity updateComplain(ComplainEntity complainEntity);

    ChatEntity startProcessionComplain(ComplainEntity complainEntity, User user);

    Collection<ComplainEntity> getComplains();
}

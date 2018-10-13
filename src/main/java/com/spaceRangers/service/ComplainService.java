package com.spaceRangers.service;

import com.spaceRangers.entities.ComplainEntity;

public interface ComplainService {

    /**
     * Создание жалобы
     * @param complainEntity
     * @return
     */
    boolean createComplain(ComplainEntity complainEntity);


    /**
     * Изменение жалобы (если исправлена или т.д.)
     * @param complainEntity
     * @return
     */
    boolean updateComplain(ComplainEntity complainEntity);
}

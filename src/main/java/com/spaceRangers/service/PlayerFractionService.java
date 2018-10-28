package com.spaceRangers.service;

import com.spaceRangers.entities.StateUserFractionEntity;
import com.spaceRangers.entities.UserFractionEntity;

public interface PlayerFractionService extends FractionService {

    /**
     * Изменение состояния пользователя во фракции
     * (когда хочет покинуть её)
     * @return
     */
    UserFractionEntity updateStateUserFraction(UserFractionEntity userFractionEntity);
}

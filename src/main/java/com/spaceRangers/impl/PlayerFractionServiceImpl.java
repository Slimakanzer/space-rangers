package com.spaceRangers.impl;

import com.spaceRangers.entities.StateUserFractionEntity;
import com.spaceRangers.entities.TaskEntity;
import com.spaceRangers.entities.UserFractionEntity;
import com.spaceRangers.repository.UserFractionRepository;
import com.spaceRangers.service.PlayerFractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("playerFractionService")
public class PlayerFractionServiceImpl extends FractionServiceImpl implements PlayerFractionService {

    @Autowired
    UserFractionRepository userFractionRepository;

    /**
     * Изменение состояния пользователя во фракции
     * (когда хочет покинуть её)
     *
     * @return
     */
    @Override
    public boolean updateStateUserFraction(UserFractionEntity userFractionEntity) {
        userFractionRepository.save(userFractionEntity);
        return true;
    }

    @Override
    public List<TaskEntity> getFractionTasks(int idFraction) {
        return super.getFractionTasks(idFraction);
    }
}

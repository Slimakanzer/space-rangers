package com.spaceRangers.impl;

import com.spaceRangers.entities.StateUserFractionEntity;
import com.spaceRangers.entities.TaskEntity;
import com.spaceRangers.entities.UserFractionEntity;
import com.spaceRangers.repository.FractionRepository;
import com.spaceRangers.repository.UserFractionRepository;
import com.spaceRangers.repository.UserRepository;
import com.spaceRangers.service.PlayerFractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("playerFractionService")
public class PlayerFractionServiceImpl extends FractionServiceImpl implements PlayerFractionService {

    public PlayerFractionServiceImpl(UserFractionRepository userFractionRepository, FractionRepository fractionRepository, UserRepository userRepository) {
        super(userFractionRepository, fractionRepository, userRepository);
    }

    /**
     * Изменение состояния пользователя во фракции
     * (когда хочет покинуть её)
     *
     * @return
     */
    @Override
    public UserFractionEntity updateStateUserFraction(UserFractionEntity userFractionEntity) {
        userFractionRepository.save(userFractionEntity);
        return userFractionEntity;
    }

    @Override
    public List<TaskEntity> getFractionTasks(int idFraction) {
        // TODO сделать реализацию
        return super.getFractionTasks(idFraction);
    }
}

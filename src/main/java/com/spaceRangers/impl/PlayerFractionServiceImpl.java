package com.spaceRangers.impl;

import com.spaceRangers.entities.FractionEntity;
import com.spaceRangers.entities.StateUserFractionEntity;
import com.spaceRangers.entities.TaskEntity;
import com.spaceRangers.entities.UserFractionEntity;
import com.spaceRangers.repository.*;
import com.spaceRangers.service.PlayerFractionService;
import com.spaceRangers.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service("playerFractionService")
public class PlayerFractionServiceImpl extends FractionServiceImpl implements PlayerFractionService {

    public PlayerFractionServiceImpl(UserFractionRepository userFractionRepository, FractionRepository fractionRepository, UserRepository userRepository, StateUserFractionRepository stateUserFractionRepository, TaskRepository taskRepository, RegistrationService registrationService) {
        super(userFractionRepository, fractionRepository, userRepository, stateUserFractionRepository, taskRepository, registrationService);
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


    public List<TaskEntity> getFractionTasks(FractionEntity fration) {

        return fration.getTasks()
                .stream()
                .filter(e->{
                    if(
                            e.getStatePrivacy().getName().equals("fraction")
                            || e.getStatePrivacy().getName().equals("public")
                    ) return true;
                    return false;
                })
                .collect(Collectors.toList());
        // TODO сделать реализацию
    }

    @Override
    public TaskEntity getFractionTask(int id) {
        TaskEntity taskEntity = taskRepository.findById(id).get();
        if(taskEntity.getStatePrivacy().getName().equals("protected")) return taskEntity;
        return super.getFractionTask(id);
    }
}

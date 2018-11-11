package com.spaceRangers.impl;

import com.spaceRangers.entities.StateUserFractionEntity;
import com.spaceRangers.entities.TaskEntity;
import com.spaceRangers.entities.UserFractionEntity;
import com.spaceRangers.repository.*;
import com.spaceRangers.service.LeaderPlayerFractionService;
import com.spaceRangers.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "leaderPlayerFractionService")
public class LeaderPlayerFractionServiceImpl extends AdvisersPlayerFractionServiceImpl implements LeaderPlayerFractionService {

    private final UserFractionRepository userFractionRepository;

    @Autowired
    public LeaderPlayerFractionServiceImpl(UserFractionRepository userFractionRepository, FractionRepository fractionRepository, UserRepository userRepository, TaskRepository taskRepository, StateUserFractionRepository stateUserFractionRepository, RegistrationService registrationService) {
        super(userFractionRepository, fractionRepository, userRepository, taskRepository, stateUserFractionRepository, registrationService);
        this.userFractionRepository = userFractionRepository;
    }

    @Override
    public TaskEntity updateTask(TaskEntity task) {
        super.taskRepository.save(task);
        return task;
    }

    @Override
    public boolean removeTask(TaskEntity task) {
        taskRepository.delete(task);
        return true;
    }

    @Override
    public UserFractionEntity updateStateUserFraction(UserFractionEntity userFractionEntity) {
        userFractionRepository.save(userFractionEntity);
        return userFractionEntity;
    }
}

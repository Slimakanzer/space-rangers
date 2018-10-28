package com.spaceRangers.impl;

import com.spaceRangers.entities.StateUserFractionEntity;
import com.spaceRangers.entities.TaskEntity;
import com.spaceRangers.entities.UserFractionEntity;
import com.spaceRangers.repository.FractionRepository;
import com.spaceRangers.repository.TaskRepository;
import com.spaceRangers.repository.UserFractionRepository;
import com.spaceRangers.repository.UserRepository;
import com.spaceRangers.service.LeaderPlayerFractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "leaderPlayerFractionService")
public class LeaderPlayerFractionServiceImpl extends AdvisersPlayerFractionServiceImpl implements LeaderPlayerFractionService {

    private final UserFractionRepository userFractionRepository;

    @Autowired
    public LeaderPlayerFractionServiceImpl(UserFractionRepository userFractionRepository, FractionRepository fractionRepository, UserRepository userRepository, TaskRepository taskRepository) {
        super(userFractionRepository, fractionRepository, userRepository, taskRepository);
        this.userFractionRepository = userFractionRepository;
    }

    @Override
    public List<TaskEntity> getFractionTasks(int idFraction) {
        // TODO сделать реализацию
        return super.getFractionTasks(idFraction);
    }

    /**
     * Обновление задачи фракции
     * Например, обновить область видимости
     * или принять задачу
     *
     * @param task
     * @return
     */
    @Override
    public TaskEntity updateTask(TaskEntity task) {
        super.taskRepository.save(task);
        return task;
    }

    /**
     * Удаление задачи фракции
     *
     * @param task
     * @return
     */
    @Override
    public boolean removeTask(TaskEntity task) {
        taskRepository.delete(task);
        return true;
    }

    /**
     * Обновление состояния пользователя во фракции
     * Например, увольнение
     * или принятие пользователя
     *
     * @return
     */
    @Override
    public UserFractionEntity updateStateUserFraction(UserFractionEntity userFractionEntity) {
        userFractionRepository.save(userFractionEntity);
        return userFractionEntity;
    }
}

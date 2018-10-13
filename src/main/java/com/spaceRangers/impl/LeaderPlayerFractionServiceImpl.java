package com.spaceRangers.impl;

import com.spaceRangers.entities.StateUserFractionEntity;
import com.spaceRangers.entities.TaskEntity;
import com.spaceRangers.entities.UserFractionEntity;
import com.spaceRangers.repository.TaskRepository;
import com.spaceRangers.repository.UserFractionRepository;
import com.spaceRangers.service.LeaderPlayerFractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "leaderPlayerFractionService")
public class LeaderPlayerFractionServiceImpl extends AdvisersPlayerFractionServiceImpl implements LeaderPlayerFractionService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserFractionRepository userFractionRepository;

    @Override
    public List<TaskEntity> getFractionTasks(int idFraction) {
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
    public boolean updateTask(TaskEntity task) {
        taskRepository.save(task);
        return true;
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
    public boolean updateStateUserFraction(UserFractionEntity userFractionEntity) {
        userFractionRepository.save(userFractionEntity);
        return true;
    }
}

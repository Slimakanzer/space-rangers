package com.spaceRangers.impl;

import com.spaceRangers.entities.TaskEntity;
import com.spaceRangers.repository.TaskRepository;
import com.spaceRangers.service.AdvisersPlayerFractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("advisersPlayerFractionService")
public class AdvisersPlayerFractionServiceImpl extends PlayerFractionServiceImpl implements AdvisersPlayerFractionService {

    @Autowired
    TaskRepository taskRepository;
    @Override
    public List<TaskEntity> getFractionTasks(int idFraction) {
        return super.getFractionTasks(idFraction);
    }

    /**
     * Создание задач фракции
     *
     * @param task
     * @return
     */
    @Override
    public boolean createTask(TaskEntity task) {
        taskRepository.save(task);
        return true;
    }
}

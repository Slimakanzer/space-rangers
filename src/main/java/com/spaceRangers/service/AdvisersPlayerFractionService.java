package com.spaceRangers.service;

import com.spaceRangers.entities.TaskEntity;

public interface AdvisersPlayerFractionService extends PlayerFractionService {

    /**
     * Создание задач фракции
     * @param task
     * @return
     */
    TaskEntity createTask(TaskEntity task);
}

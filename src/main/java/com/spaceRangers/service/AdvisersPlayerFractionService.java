package com.spaceRangers.service;

import com.spaceRangers.entities.TaskEntity;

public interface AdvisersPlayerFractionService extends PlayerFractionService {

    /**
     * Создание задач фракции
     * @param task
     * @return
     */
    boolean createTask(TaskEntity task);
}

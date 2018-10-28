package com.spaceRangers.service;

import com.spaceRangers.entities.StateUserFractionEntity;
import com.spaceRangers.entities.TaskEntity;
import com.spaceRangers.entities.UserFractionEntity;

public interface LeaderPlayerFractionService extends AdvisersPlayerFractionService {

    /**
     * Обновление задачи фракции
     * Например, обновить область видимости
     * или принять задачу
     * @param task
     * @return
     */
    TaskEntity updateTask(TaskEntity task);

    /**
     * Удаление задачи фракции
     * @param task
     * @return
     */
    boolean removeTask(TaskEntity task);

    /**
     * Обновление состояния пользователя во фракции
     * Например, увольнение
     * или принятие пользователя
     * @return
     */
    UserFractionEntity updateStateUserFraction(UserFractionEntity userFractionEntity);
}

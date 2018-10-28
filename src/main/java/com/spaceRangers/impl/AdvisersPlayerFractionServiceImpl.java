package com.spaceRangers.impl;

import com.spaceRangers.entities.TaskEntity;
import com.spaceRangers.repository.FractionRepository;
import com.spaceRangers.repository.TaskRepository;
import com.spaceRangers.repository.UserFractionRepository;
import com.spaceRangers.repository.UserRepository;
import com.spaceRangers.service.AdvisersPlayerFractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("advisersPlayerFractionService")
public class AdvisersPlayerFractionServiceImpl extends PlayerFractionServiceImpl implements AdvisersPlayerFractionService {

    final TaskRepository taskRepository;

    @Autowired
    public AdvisersPlayerFractionServiceImpl(UserFractionRepository userFractionRepository, FractionRepository fractionRepository, UserRepository userRepository, TaskRepository taskRepository) {
        super(userFractionRepository, fractionRepository, userRepository);
        this.taskRepository = taskRepository;
    }


    @Override
    public List<TaskEntity> getFractionTasks(int idFraction) {
        // TODO сделать реализацию
        return super.getFractionTasks(idFraction);
    }

    /**
     * Создание задач фракции
     *
     * @param task
     * @return
     */
    @Override
    public TaskEntity createTask(TaskEntity task) {
        taskRepository.save(task);
        return task;
    }
}

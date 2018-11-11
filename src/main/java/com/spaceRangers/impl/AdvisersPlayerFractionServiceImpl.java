package com.spaceRangers.impl;

import com.spaceRangers.entities.FractionEntity;
import com.spaceRangers.entities.StateUserFractionEntity;
import com.spaceRangers.entities.TaskEntity;
import com.spaceRangers.repository.*;
import com.spaceRangers.service.AdvisersPlayerFractionService;
import com.spaceRangers.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("advisersPlayerFractionService")
public class AdvisersPlayerFractionServiceImpl extends PlayerFractionServiceImpl implements AdvisersPlayerFractionService {


    @Autowired
    StateTaskRepository stateTaskRepository;

    @Autowired
    StatePrivacyRepository statePrivacyRepository;

    @Autowired
    public AdvisersPlayerFractionServiceImpl(UserFractionRepository userFractionRepository, FractionRepository fractionRepository, UserRepository userRepository, TaskRepository taskRepository, StateUserFractionRepository stateUserFractionRepository, RegistrationService registrationService) {
        super(userFractionRepository, fractionRepository, userRepository, stateUserFractionRepository, taskRepository, registrationService);
    }


    @Override
    public List<TaskEntity> getFractionTasks(FractionEntity fraction) {
        // TODO сделать реализацию
        return (ArrayList<TaskEntity>)fraction.getTasks();
    }


    @Transactional
    public TaskEntity createTask(TaskEntity task) {
        task.setStateTask(stateTaskRepository.findStateTaskEntityByName("created"));

        if(task.getStatePrivacy() == null){
            task.setStatePrivacy(statePrivacyRepository.findStatePrivacyEntityByName("public"));
        }

        if(task.getStatePrivacy() == null || task.getStateTask() == null || task.getTypeTask() == null){
            throw new NullPointerException();
        }


        taskRepository.save(task);
        return task;
    }

    @Override
    public TaskEntity getFractionTask(int id) {

        TaskEntity taskEntity = taskRepository.findById(id).get();

        if(taskEntity.getStatePrivacy().getName().equals("private")) return taskEntity;
        return super.getFractionTask(id);
    }
}

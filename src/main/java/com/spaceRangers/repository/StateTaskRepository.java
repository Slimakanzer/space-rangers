package com.spaceRangers.repository;

import com.spaceRangers.entities.StateTaskEntity;
import org.springframework.data.repository.CrudRepository;

public interface StateTaskRepository extends CrudRepository<StateTaskEntity, Integer> {
    StateTaskEntity findStateTaskEntityByName(String name);
}

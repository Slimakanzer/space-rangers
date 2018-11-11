package com.spaceRangers.repository;

import com.spaceRangers.entities.SystemEntity;
import org.springframework.data.repository.CrudRepository;

public interface SystemRepository extends CrudRepository<SystemEntity, Integer> {
    SystemEntity findSystemEntityByNameSystem(String name);
}

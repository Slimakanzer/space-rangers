package com.spaceRangers.repository;

import com.spaceRangers.entities.StateUserFractionEntity;
import org.springframework.data.repository.CrudRepository;

public interface StateUserFractionRepository extends CrudRepository<StateUserFractionEntity, Integer> {

    StateUserFractionEntity findStateUserFractionEntityByName(String name);
}

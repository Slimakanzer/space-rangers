package com.spaceRangers.repository;

import com.spaceRangers.entities.StatePrivacyEntity;
import org.springframework.data.repository.CrudRepository;

public interface StatePrivacyRepository extends CrudRepository<StatePrivacyEntity, Integer> {

    StatePrivacyEntity findStatePrivacyEntityByName(String name);

}

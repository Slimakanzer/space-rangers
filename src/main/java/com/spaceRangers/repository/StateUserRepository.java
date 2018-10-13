package com.spaceRangers.repository;

import com.spaceRangers.entities.StateUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateUserRepository extends JpaRepository<StateUserEntity, Integer> {
}

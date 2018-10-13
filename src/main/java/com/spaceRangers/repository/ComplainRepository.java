package com.spaceRangers.repository;

import com.spaceRangers.entities.ComplainEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplainRepository extends JpaRepository<ComplainEntity, Integer> {
}

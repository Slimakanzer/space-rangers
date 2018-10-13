package com.spaceRangers.repository;

import com.spaceRangers.entities.VotingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotingRepository extends JpaRepository<VotingEntity, Integer> {
}

package com.spaceRangers.repository;

import com.spaceRangers.entities.PoliticsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PoliticsRepository extends JpaRepository<PoliticsEntity, Integer> {
}

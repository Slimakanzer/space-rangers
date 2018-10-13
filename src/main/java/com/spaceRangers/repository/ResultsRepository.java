package com.spaceRangers.repository;

import com.spaceRangers.entities.ResourceEntity;
import com.spaceRangers.entities.ResultsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultsRepository extends JpaRepository<ResultsEntity, Integer> {
}

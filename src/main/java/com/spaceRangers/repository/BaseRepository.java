package com.spaceRangers.repository;

import com.spaceRangers.entities.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository extends JpaRepository<BaseEntity, Integer> {
}

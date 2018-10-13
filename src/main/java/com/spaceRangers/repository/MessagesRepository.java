package com.spaceRangers.repository;

import com.spaceRangers.entities.MessagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessagesRepository extends JpaRepository<MessagesEntity, Integer> {
}

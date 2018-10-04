package com.spaceRangers.repository;

import com.spaceRangers.entities.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ChatEntityRepository extends JpaRepository<ChatEntity, Integer> {
}

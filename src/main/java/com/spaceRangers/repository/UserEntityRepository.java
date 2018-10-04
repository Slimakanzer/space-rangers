package com.spaceRangers.repository;

import com.spaceRangers.entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<UsersEntity, Integer> {
}

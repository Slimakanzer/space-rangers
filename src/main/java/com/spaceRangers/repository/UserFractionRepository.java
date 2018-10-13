package com.spaceRangers.repository;

import com.spaceRangers.entities.UserFractionEntity;
import com.spaceRangers.entities.UserFractionEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFractionRepository extends JpaRepository<UserFractionEntity, UserFractionEntityPK> {
}

package com.spaceRangers.repository;

import com.spaceRangers.entities.UserBattleEntity;
import com.spaceRangers.entities.UserBattleEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBattleRepository extends JpaRepository<UserBattleEntity, UserBattleEntityPK> {
}

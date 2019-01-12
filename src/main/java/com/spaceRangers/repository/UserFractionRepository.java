package com.spaceRangers.repository;

import com.spaceRangers.entities.UserFractionEntity;
import com.spaceRangers.entities.UserFractionEntityPK;
import com.spaceRangers.entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserFractionRepository extends JpaRepository<UserFractionEntity, UserFractionEntityPK> {

    Optional<UserFractionRepository> findUserFractionEntityByUser(UsersEntity usersEntity);
}

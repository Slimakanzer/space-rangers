package com.spaceRangers.repository;

import com.spaceRangers.entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<UsersEntity, Integer> {

    @Query(value = "SELECT user from UsersEntity user where user.login = :login")
    UsersEntity findUsersEntityByLogin(@Param("login") String login);

    @Query(value = "SELECT user from UsersEntity user where user.level = :level")
    List<UsersEntity> findUsersByLevel(@Param("level") int level);
}

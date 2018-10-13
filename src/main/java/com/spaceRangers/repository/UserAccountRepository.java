package com.spaceRangers.repository;

import com.spaceRangers.entities.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserAccountRepository extends JpaRepository<UserAccountEntity, Integer> {

    @Query("SELECT userAccount from UserAccountEntity  userAccount where userAccount.login = :login")
    UserAccountEntity getUserAccountByLogin(@Param("login") String login);
}

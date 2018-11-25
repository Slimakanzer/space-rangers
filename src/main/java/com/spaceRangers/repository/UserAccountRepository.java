package com.spaceRangers.repository;

import com.spaceRangers.entities.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserAccountRepository extends CrudRepository<UserAccountEntity, Integer> {

    UserAccountEntity findUserAccountEntityByLogin(String login);

    Optional<UserAccountEntity> findUserAccountEntityByMail(String mail);
}

package com.spaceRangers.repository;

import com.spaceRangers.entities.UserGroupEntity;
import com.spaceRangers.entities.UserGroupEntityPK;
import com.spaceRangers.entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserGroupRepository extends JpaRepository<UserGroupEntity, UserGroupEntityPK> {

    @Query(value = "SELECT userGroup from UserGroupEntity userGroup where userGroup.idUser = :idUser")
    List<UserGroupEntity> getUsersGroup(@Param("idUser")int idUser);
}

package com.spaceRangers.repository;

import com.spaceRangers.entities.GroupsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupsRepository extends JpaRepository<GroupsEntity, Integer> {


    @Query("SELECT groups from GroupsEntity groups join UserGroupEntity userGroup on groups.id = userGroup.idGroup where userGroup.idUser = :idUser")
    List<GroupsEntity> getListGroupsUser(@Param("idUser") int idUser);
}
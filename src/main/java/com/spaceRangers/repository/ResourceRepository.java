package com.spaceRangers.repository;

import com.spaceRangers.entities.ResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResourceRepository extends JpaRepository<ResourceEntity, Integer> {

    @Query("select resource from UsersEntity user join PlanetEntity planet on user.id = planet.user.id join ResourceEntity resource on planet.id = resource.planet.id where user.id = :idUser")
    List<ResourceEntity> getResourceOfUser(@Param("idUser") int idUser);
}

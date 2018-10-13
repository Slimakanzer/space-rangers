package com.spaceRangers.repository;

import com.spaceRangers.entities.PlanetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlanetRepository extends JpaRepository<PlanetEntity, Integer> {


    @Query("SELECT planet from PlanetEntity planet where planet.idUser = :idUser")
    List<PlanetEntity> getListPlanetUser(@Param("idUser") int idUser);
}

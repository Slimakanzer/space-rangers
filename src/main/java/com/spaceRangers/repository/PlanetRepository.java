package com.spaceRangers.repository;

import com.spaceRangers.entities.PlanetEntity;
import com.spaceRangers.entities.UsersEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlanetRepository extends CrudRepository<PlanetEntity, Integer> {

    List<PlanetEntity> findPlanetEntitiesByUsersByIdUser(UsersEntity UsersByIdUser);

    PlanetEntity findPlanetEntityByNamePlanet(String namePlanet);
}

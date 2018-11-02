package com.spaceRangers.repository;

import com.spaceRangers.entities.BaseEntity;
import com.spaceRangers.entities.ShipEntity;
import com.spaceRangers.entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShipRepository extends CrudRepository<ShipEntity, Integer> {

   List<ShipEntity> findShipEntitiesByUser(UsersEntity usersByIdUser);

   List<ShipEntity> findShipEntitiesByBase(BaseEntity baseByIdBase);
}

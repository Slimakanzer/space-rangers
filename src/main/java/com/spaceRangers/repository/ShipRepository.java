package com.spaceRangers.repository;

import com.spaceRangers.entities.ShipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShipRepository extends JpaRepository<ShipEntity, Integer> {

    @Query("SELECT ship from ShipEntity ship where ship.idUser = :idUser")
    List<ShipEntity> getListShipsUser(@Param("idUser") int idUser);
}

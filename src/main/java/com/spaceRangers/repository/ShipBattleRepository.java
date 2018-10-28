package com.spaceRangers.repository;

import com.spaceRangers.entities.ShipBattleEntity;
import com.spaceRangers.entities.ShipBattleEntityPK;
import com.spaceRangers.entities.ShipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShipBattleRepository extends CrudRepository<ShipBattleEntity, ShipBattleEntityPK> {

    @Query("select ship from ShipBattleEntity shipBattle join ShipEntity ship on shipBattle.idShip = ship.id where shipBattle.idBattle = :idBattle")
    List<ShipEntity> findShipsInBattle(@Param("idBattle")int idBattle);

}

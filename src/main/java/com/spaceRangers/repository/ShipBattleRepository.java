package com.spaceRangers.repository;

import com.spaceRangers.entities.ShipBattleEntity;
import com.spaceRangers.entities.ShipBattleEntityPK;
import com.spaceRangers.entities.ShipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShipBattleRepository extends JpaRepository<ShipBattleEntity, ShipBattleEntityPK> {

    @Query(value = "SELECT ship from ShipEntity ship join ShipBattleEntity shipBattle on shipBattle.idBattle = :idBattle where ship.idUser = :idUser")
    List<ShipEntity> getListShipinBattleByIdUser(@Param("idBattle") int idBattle, @Param("idUser") int idUser);
}

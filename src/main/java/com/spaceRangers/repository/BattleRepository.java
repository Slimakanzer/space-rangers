package com.spaceRangers.repository;

import com.spaceRangers.entities.BattleEntity;
import com.spaceRangers.entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BattleRepository extends JpaRepository<BattleEntity, Integer> {

    @Query("select battle from BattleEntity battle join UserBattleEntity userBattle on battle.id = userBattle.idBattle where userBattle.idUser = :idUser")
    List<BattleEntity> getListBattleUser(@Param("idUser") int idUser);

    @Query(value = "SELECT user from UsersEntity user join UserBattleEntity userBattle on user.id = userBattle.idUser where userBattle.idBattle = :idBattle")
    List<UsersEntity> getListUsersInBattle(@Param("idBattle") int idBattle);
}

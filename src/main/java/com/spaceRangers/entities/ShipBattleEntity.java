package com.spaceRangers.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "ship_battle", schema = "s242552", catalog = "course")
@IdClass(ShipBattleEntityPK.class)
public class ShipBattleEntity {
    private Integer idShip;
    private Integer idBattle;
    private ShipEntity ship;
    private BattleEntity battle;

    @Id
    @Column(name = "id_ship", nullable = false)
    public Integer getIdShip() {
        return idShip;
    }

    public void setIdShip(Integer idShip) {
        this.idShip = idShip;
    }

    @Id
    @Column(name = "id_battle", nullable = false)
    public Integer getIdBattle() {
        return idBattle;
    }

    public void setIdBattle(Integer idBattle) {
        this.idBattle = idBattle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShipBattleEntity that = (ShipBattleEntity) o;
        return Objects.equals(idShip, that.idShip) &&
                Objects.equals(idBattle, that.idBattle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idShip, idBattle);
    }

    @ManyToOne
    @JoinColumn(name = "id_ship", referencedColumnName = "id", nullable = false, updatable = false, insertable = false)
    @JsonBackReference("shipBattleShip")
    public ShipEntity getShip() {
        return ship;
    }

    public void setShip(ShipEntity ship) {
        this.ship = ship;
    }

    @ManyToOne
    @JoinColumn(name = "id_battle", referencedColumnName = "id", nullable = false, updatable = false, insertable = false)
    @JsonBackReference
    public BattleEntity getBattle() {
        return battle;
    }

    public void setBattle(BattleEntity battle) {
        this.battle = battle;
    }
}

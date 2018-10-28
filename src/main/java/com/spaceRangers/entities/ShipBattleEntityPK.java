package com.spaceRangers.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ShipBattleEntityPK implements Serializable {
    private Integer idShip;
    private Integer idBattle;

    @Column(name = "id_ship", nullable = false)
    @Id
    public Integer getIdShip() {
        return idShip;
    }

    public void setIdShip(Integer idShip) {
        this.idShip = idShip;
    }

    @Column(name = "id_battle", nullable = false)
    @Id
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
        ShipBattleEntityPK that = (ShipBattleEntityPK) o;
        return Objects.equals(idShip, that.idShip) &&
                Objects.equals(idBattle, that.idBattle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idShip, idBattle);
    }
}

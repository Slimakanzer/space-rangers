package com.spaceRangers.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class ShipBattleEntityPK implements Serializable {
    private int idShip;
    private int idBattle;

    @Column(name = "id_ship", nullable = false)
    @Id
    public int getIdShip() {
        return idShip;
    }

    public void setIdShip(int idShip) {
        this.idShip = idShip;
    }

    @Column(name = "id_battle", nullable = false)
    @Id
    public int getIdBattle() {
        return idBattle;
    }

    public void setIdBattle(int idBattle) {
        this.idBattle = idBattle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShipBattleEntityPK that = (ShipBattleEntityPK) o;

        if (idShip != that.idShip) return false;
        if (idBattle != that.idBattle) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idShip;
        result = 31 * result + idBattle;
        return result;
    }
}

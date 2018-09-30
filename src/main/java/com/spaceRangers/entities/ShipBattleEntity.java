package com.spaceRangers.entities;

import javax.persistence.*;

@Entity
@Table(name = "ship_battle", schema = "public", catalog = "course")
@IdClass(ShipBattleEntityPK.class)
public class ShipBattleEntity {
    private int idShip;
    private int idBattle;

    @Id
    @Column(name = "id_ship", nullable = false)
    public int getIdShip() {
        return idShip;
    }

    public void setIdShip(int idShip) {
        this.idShip = idShip;
    }

    @Id
    @Column(name = "id_battle", nullable = false)
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

        ShipBattleEntity that = (ShipBattleEntity) o;

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

package com.spaceRangers.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ship_battle", schema = "public", catalog = "course")
@IdClass(ShipBattleEntityPK.class)
public class ShipBattleEntity {
    private Integer idShip;
    private Integer idBattle;

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
}

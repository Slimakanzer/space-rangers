package com.spaceRangers.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class UserBattleEntityPK implements Serializable {
    private Integer idUser;
    private Integer idBattle;

    @Column(name = "id_user", nullable = false)
    @Id
    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
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
        UserBattleEntityPK that = (UserBattleEntityPK) o;
        return Objects.equals(idUser, that.idUser) &&
                Objects.equals(idBattle, that.idBattle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idBattle);
    }
}

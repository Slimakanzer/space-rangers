package com.spaceRangers.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class UserBattleEntityPK implements Serializable {
    private Integer idUser;
    private Integer idBattle;

    @Column(name = "id_user", nullable = false)
    @Basic
    @Id
    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    @Column(name = "id_battle", nullable = false)
    @Basic
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

        if (idUser != null ? !idUser.equals(that.idUser) : that.idUser != null) return false;
        if (idBattle != null ? !idBattle.equals(that.idBattle) : that.idBattle != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUser != null ? idUser.hashCode() : 0;
        result = 31 * result + (idBattle != null ? idBattle.hashCode() : 0);
        return result;
    }
}

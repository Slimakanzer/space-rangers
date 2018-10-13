package com.spaceRangers.entities;

import javax.persistence.*;

@Entity
@Table(name = "user_battle", schema = "public", catalog = "course")
@IdClass(UserBattleEntityPK.class)
public class UserBattleEntity {
    private Integer idBattle;
    private Integer idUser;

    public void setIdBattle(int idBattle) {
        this.idBattle = idBattle;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Id
    @Column(name = "id_battle", nullable = false)
    public Integer getIdBattle() {
        return idBattle;
    }

    public void setIdBattle(Integer idBattle) {
        this.idBattle = idBattle;
    }

    @Id
    @Column(name = "id_user", nullable = false)
    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserBattleEntity that = (UserBattleEntity) o;

        if (idBattle != null ? !idBattle.equals(that.idBattle) : that.idBattle != null) return false;
        if (idUser != null ? !idUser.equals(that.idUser) : that.idUser != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idBattle != null ? idBattle.hashCode() : 0;
        result = 31 * result + (idUser != null ? idUser.hashCode() : 0);
        return result;
    }
}

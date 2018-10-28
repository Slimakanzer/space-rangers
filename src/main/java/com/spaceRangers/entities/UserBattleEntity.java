package com.spaceRangers.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "user_battle", schema = "public", catalog = "course")
@IdClass(UserBattleEntityPK.class)
public class UserBattleEntity {
    private Integer idUser;
    private Integer idBattle;
    private Date date;
    private UsersEntity usersByIdUser;
    private BattleEntity battleByIdBattle;
    private StateUserBattleEntity stateUserBattleByIdState;

    @Id
    @Column(name = "id_user", nullable = false)
    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
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

    @Basic
    @Column(name = "date", nullable = true)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBattleEntity that = (UserBattleEntity) o;
        return Objects.equals(idUser, that.idUser) &&
                Objects.equals(idBattle, that.idBattle) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idBattle, date);
    }

    @ManyToOne
    @JoinColumn(name = "id_state", referencedColumnName = "id")
    public StateUserBattleEntity getStateUserBattleByIdState() {
        return stateUserBattleByIdState;
    }

    public void setStateUserBattleByIdState(StateUserBattleEntity stateUserBattleByIdState) {
        this.stateUserBattleByIdState = stateUserBattleByIdState;
    }
}

package com.spaceRangers.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "user_battle", schema = "s242552", catalog = "course")
@IdClass(UserBattleEntityPK.class)
public class UserBattleEntity {
    private Integer idUser;
    private Integer idBattle;
    private Date date;
    private UsersEntity user;
    private BattleEntity battle;
    private StateUserBattleEntity stateUserBattle;

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
    @JsonManagedReference
    public StateUserBattleEntity getStateUserBattle() {
        return stateUserBattle;
    }

    public void setStateUserBattle(StateUserBattleEntity stateUserBattleByIdState) {
        this.stateUserBattle = stateUserBattleByIdState;
    }

    @ManyToOne
    @JoinColumn(name = "id_battle", referencedColumnName = "id", nullable = false, updatable = false, insertable = false)
    @JsonBackReference
    public BattleEntity getBattle(){ return battle;}

    public void setBattle(BattleEntity battle) {
        this.battle = battle;
    }


    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @JsonBackReference
    public UsersEntity getUser() {
        return user;
    }

    public void setUser(UsersEntity user) {
        this.user = user;
    }
}

package com.spaceRangers.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "user_fraction", schema = "public", catalog = "course")
@IdClass(UserFractionEntityPK.class)
public class UserFractionEntity {
    private Integer idFraction;
    private Integer idUser;
    private Date date;
    private ShipEntity shipByIdFraction;
    private UsersEntity usersByIdUser;
    private StateUserFractionEntity stateUserFractionByIdState;

    @Id
    @Column(name = "id_fraction", nullable = false)
    public Integer getIdFraction() {
        return idFraction;
    }

    public void setIdFraction(Integer idFraction) {
        this.idFraction = idFraction;
    }

    @Id
    @Column(name = "id_user", nullable = false)
    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
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
        UserFractionEntity that = (UserFractionEntity) o;
        return Objects.equals(idFraction, that.idFraction) &&
                Objects.equals(idUser, that.idUser) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFraction, idUser, date);
    }

    @ManyToOne
    @JoinColumn(name = "id_state", referencedColumnName = "id")
    public StateUserFractionEntity getStateUserFractionByIdState() {
        return stateUserFractionByIdState;
    }

    public void setStateUserFractionByIdState(StateUserFractionEntity stateUserFractionByIdState) {
        this.stateUserFractionByIdState = stateUserFractionByIdState;
    }
}

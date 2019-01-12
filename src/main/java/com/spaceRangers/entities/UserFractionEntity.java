package com.spaceRangers.entities;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "user_fraction", schema = "s242552", catalog = "course")
@IdClass(UserFractionEntityPK.class)
public class UserFractionEntity {
    private Integer idFraction;
    private Integer idUser;
    private Date date;
    private UsersEntity user;
    private FractionEntity fraction;
    private StateUserFractionEntity stateUserFraction;

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
    @JsonIdentityInfo(generator = ObjectIdGenerators.None.class)
    public StateUserFractionEntity getStateUserFraction() {
        return stateUserFraction;
    }

    public void setStateUserFraction(StateUserFractionEntity stateUserFractionByIdState) {
        this.stateUserFraction = stateUserFractionByIdState;
    }

    @ManyToOne
    @JoinColumn(name = "id_fraction", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonBackReference(value = "fractionUserFraction")
    public FractionEntity getFraction(){return fraction;}

    public void setFraction(FractionEntity fraction) {
        this.fraction = fraction;
    }

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.None.class)
    public UsersEntity getUser(){
        return user;
    }

    public void setUser(UsersEntity user) {
        this.user = user;
    }
}

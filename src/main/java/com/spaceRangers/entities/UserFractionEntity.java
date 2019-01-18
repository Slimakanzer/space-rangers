package com.spaceRangers.entities;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "user_fraction")
public class UserFractionEntity {
    private Integer idFraction;
    private Integer idUser;
    private Date date;
    private UsersEntity user;
    private FractionEntity fraction;
    private StateUserFractionEntity stateUserFraction;

    @Id
    @Column(name = "id_user", nullable = false)
    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    @Column(name = "date", nullable = false)
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
    @JoinColumn(name = "id_fraction", referencedColumnName = "id")
    public FractionEntity getFraction(){return fraction;}

    public void setFraction(FractionEntity fraction) {
        this.fraction = fraction;
    }

    @OneToOne(mappedBy = "userFraction")
    @JsonIdentityInfo(generator = ObjectIdGenerators.None.class)
    public UsersEntity getUser(){
        return user;
    }

    public void setUser(UsersEntity user) {
        this.user = user;
    }
}

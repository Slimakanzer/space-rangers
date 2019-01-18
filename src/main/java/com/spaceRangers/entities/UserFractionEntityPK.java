package com.spaceRangers.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class UserFractionEntityPK implements Serializable {
    private Integer idFraction;
    private Integer idUser;
    private Date date;

    @Column(name = "id_fraction", nullable = false)
    @Id
    public Integer getIdFraction() {
        return idFraction;
    }

    public void setIdFraction(Integer idFraction) {
        this.idFraction = idFraction;
    }

    @Column(name = "id_user", nullable = false)
    @Id
    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    @Column(name = "date", nullable = false)
    @Id
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
        UserFractionEntityPK that = (UserFractionEntityPK) o;
        return Objects.equals(idFraction, that.idFraction) &&
                Objects.equals(idUser, that.idUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFraction, idUser);
    }
}

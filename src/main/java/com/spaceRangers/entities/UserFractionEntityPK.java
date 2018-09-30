package com.spaceRangers.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class UserFractionEntityPK implements Serializable {
    private int idFraction;
    private int idUser;

    @Column(name = "id_fraction", nullable = false)
    @Id
    public int getIdFraction() {
        return idFraction;
    }

    public void setIdFraction(int idFraction) {
        this.idFraction = idFraction;
    }

    @Column(name = "id_user", nullable = false)
    @Id
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserFractionEntityPK that = (UserFractionEntityPK) o;

        if (idFraction != that.idFraction) return false;
        if (idUser != that.idUser) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idFraction;
        result = 31 * result + idUser;
        return result;
    }
}

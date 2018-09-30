package com.spaceRangers.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "user_fraction", schema = "public", catalog = "course")
@IdClass(UserFractionEntityPK.class)
public class UserFractionEntity {
    private int idFraction;
    private int idUser;
    private Date date;
    private Boolean actual;

    @Id
    @Column(name = "id_fraction", nullable = false)
    public int getIdFraction() {
        return idFraction;
    }

    public void setIdFraction(int idFraction) {
        this.idFraction = idFraction;
    }

    @Id
    @Column(name = "id_user", nullable = false)
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
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

    @Basic
    @Column(name = "actual", nullable = true)
    public Boolean getActual() {
        return actual;
    }

    public void setActual(Boolean actual) {
        this.actual = actual;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserFractionEntity that = (UserFractionEntity) o;

        if (idFraction != that.idFraction) return false;
        if (idUser != that.idUser) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (actual != null ? !actual.equals(that.actual) : that.actual != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idFraction;
        result = 31 * result + idUser;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (actual != null ? actual.hashCode() : 0);
        return result;
    }
}

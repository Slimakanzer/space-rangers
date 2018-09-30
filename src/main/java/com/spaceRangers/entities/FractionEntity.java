package com.spaceRangers.entities;

import javax.persistence.*;

@Entity
@Table(name = "fraction", schema = "public", catalog = "course")
public class FractionEntity {
    private int id;
    private String nameFraction;
    private int idPolitics;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name_fraction", nullable = false, length = 15)
    public String getNameFraction() {
        return nameFraction;
    }

    public void setNameFraction(String nameFraction) {
        this.nameFraction = nameFraction;
    }

    @Basic
    @Column(name = "id_politics", nullable = false)
    public int getIdPolitics() {
        return idPolitics;
    }

    public void setIdPolitics(int idPolitics) {
        this.idPolitics = idPolitics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FractionEntity that = (FractionEntity) o;

        if (id != that.id) return false;
        if (idPolitics != that.idPolitics) return false;
        if (nameFraction != null ? !nameFraction.equals(that.nameFraction) : that.nameFraction != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nameFraction != null ? nameFraction.hashCode() : 0);
        result = 31 * result + idPolitics;
        return result;
    }
}

package com.spaceRangers.entities;

import javax.persistence.*;

@Entity
@Table(name = "politics", schema = "public", catalog = "course")
public class PoliticsEntity {
    private int id;
    private String namePolitics;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name_politics", nullable = true, length = -1)
    public String getNamePolitics() {
        return namePolitics;
    }

    public void setNamePolitics(String namePolitics) {
        this.namePolitics = namePolitics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PoliticsEntity that = (PoliticsEntity) o;

        if (id != that.id) return false;
        if (namePolitics != null ? !namePolitics.equals(that.namePolitics) : that.namePolitics != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (namePolitics != null ? namePolitics.hashCode() : 0);
        return result;
    }
}

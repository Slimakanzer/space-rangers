package com.spaceRangers.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "politics", schema = "public", catalog = "course")
public class PoliticsEntity {
    private Integer id;
    private String namePolitics;
    private Collection<FractionEntity> fractionsById;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
        return Objects.equals(id, that.id) &&
                Objects.equals(namePolitics, that.namePolitics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, namePolitics);
    }

    @OneToMany(mappedBy = "politicsByIdPolitics")
    public Collection<FractionEntity> getFractionsById() {
        return fractionsById;
    }

    public void setFractionsById(Collection<FractionEntity> fractionsById) {
        this.fractionsById = fractionsById;
    }
}

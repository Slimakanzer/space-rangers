package com.spaceRangers.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Entity
@Table(name = "politics", schema = "s242552", catalog = "course")
public class PoliticsEntity {
    private Integer id;
    private String namePolitics;
    private Collection<FractionEntity> fractions;

    public PoliticsEntity(){
        this.fractions = new HashSet<>();
    }

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

    @OneToMany(mappedBy = "politics")
    @JsonIgnore
    public Collection<FractionEntity> getFractions() {
        return fractions;
    }

    public void setFractions(Collection<FractionEntity> fractionsById) {
        this.fractions = fractionsById;
    }
}

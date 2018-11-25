package com.spaceRangers.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Entity
@Table(name = "state_user_fraction", schema = "s242552", catalog = "course")
public class StateUserFractionEntity {
    private Integer id;
    private String name;
    private Collection<UserFractionEntity> userFractions;

    public StateUserFractionEntity(){
        this.userFractions = new HashSet<>();
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
    @Column(name = "name", nullable = true, length = 30)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StateUserFractionEntity that = (StateUserFractionEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @OneToMany(mappedBy = "stateUserFraction")
    @JsonIgnore
    public Collection<UserFractionEntity> getUserFractions() {
        return userFractions;
    }

    public void setUserFractions(Collection<UserFractionEntity> userFractionsById) {
        this.userFractions = userFractionsById;
    }
}

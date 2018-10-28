package com.spaceRangers.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "state_user_fraction", schema = "public", catalog = "course")
public class StateUserFractionEntity {
    private Integer id;
    private String name;
    private Collection<UserFractionEntity> userFractionsById;

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

    @OneToMany(mappedBy = "stateUserFractionByIdState")
    public Collection<UserFractionEntity> getUserFractionsById() {
        return userFractionsById;
    }

    public void setUserFractionsById(Collection<UserFractionEntity> userFractionsById) {
        this.userFractionsById = userFractionsById;
    }
}

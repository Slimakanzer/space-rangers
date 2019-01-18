package com.spaceRangers.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "state_complain")
public class StateComplainEntity {
    private String name;
    private Collection<ComplainEntity> complains;

    @Id
    @Column(name = "name")
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
        StateComplainEntity that = (StateComplainEntity) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @OneToMany(mappedBy = "stateComplain")
    @JsonIgnore
    public Collection<ComplainEntity> getComplains() {
        return complains;
    }

    public void setComplains(Collection<ComplainEntity> complains) {
        this.complains = complains;
    }
}

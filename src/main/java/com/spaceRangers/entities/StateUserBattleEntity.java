package com.spaceRangers.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Entity
@Table(name = "state_user_battle")
public class StateUserBattleEntity {
    private Integer id;
    private String name;
    private Collection<UserBattleEntity> userBattles;

    public StateUserBattleEntity(){
        this.userBattles = new HashSet<>();
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
        StateUserBattleEntity that = (StateUserBattleEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @OneToMany(mappedBy = "stateUserBattle")
    @JsonBackReference
    public Collection<UserBattleEntity> getUserBattles() {
        return userBattles;
    }

    public void setUserBattles(Collection<UserBattleEntity> userBattlesById) {
        this.userBattles = userBattlesById;
    }
}

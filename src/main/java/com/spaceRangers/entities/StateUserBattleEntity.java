package com.spaceRangers.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "state_user_battle", schema = "public", catalog = "course")
public class StateUserBattleEntity {
    private Integer id;
    private String name;
    private Collection<UserBattleEntity> userBattlesById;

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

    @OneToMany(mappedBy = "stateUserBattleByIdState")
    public Collection<UserBattleEntity> getUserBattlesById() {
        return userBattlesById;
    }

    public void setUserBattlesById(Collection<UserBattleEntity> userBattlesById) {
        this.userBattlesById = userBattlesById;
    }
}

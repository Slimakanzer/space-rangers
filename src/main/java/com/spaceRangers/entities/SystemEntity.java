package com.spaceRangers.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "system", schema = "public", catalog = "course")
public class SystemEntity {
    private Integer id;
    private String nameSystem;
    private Collection<BaseEntity> basesById;
    private Collection<BattleEntity> battlesById;
    private Collection<PlanetEntity> planetsById;
    private Collection<ShipEntity> shipsById;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name_system", nullable = true, length = 15)
    public String getNameSystem() {
        return nameSystem;
    }

    public void setNameSystem(String nameSystem) {
        this.nameSystem = nameSystem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SystemEntity that = (SystemEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(nameSystem, that.nameSystem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameSystem);
    }

    @OneToMany(mappedBy = "systemByIdSystem")
    public Collection<BaseEntity> getBasesById() {
        return basesById;
    }

    public void setBasesById(Collection<BaseEntity> basesById) {
        this.basesById = basesById;
    }

    @OneToMany(mappedBy = "systemByIdSystem")
    public Collection<BattleEntity> getBattlesById() {
        return battlesById;
    }

    public void setBattlesById(Collection<BattleEntity> battlesById) {
        this.battlesById = battlesById;
    }

    @OneToMany(mappedBy = "systemByIdSystem")
    public Collection<PlanetEntity> getPlanetsById() {
        return planetsById;
    }

    public void setPlanetsById(Collection<PlanetEntity> planetsById) {
        this.planetsById = planetsById;
    }

    @OneToMany(mappedBy = "systemByIdSystem")
    public Collection<ShipEntity> getShipsById() {
        return shipsById;
    }

    public void setShipsById(Collection<ShipEntity> shipsById) {
        this.shipsById = shipsById;
    }
}

package com.spaceRangers.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Entity
@Table(name = "system", schema = "public", catalog = "course")
public class SystemEntity {
    private Integer id;
    private String nameSystem;
    private Collection<BaseEntity> bases;
    private Collection<BattleEntity> battles;
    private Collection<PlanetEntity> planets;
    private Collection<ShipEntity> ships;

    public SystemEntity(){
        this.bases = new HashSet<>();
        this.battles = new HashSet<>();
        this.planets = new HashSet<>();
        this.ships = new HashSet<>();
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

    @OneToMany(mappedBy = "system")
    @JsonBackReference
    public Collection<BaseEntity> getBases() {
        return bases;
    }

    public void setBases(Collection<BaseEntity> basesById) {
        this.bases = basesById;
    }

    @OneToMany(mappedBy = "system")
    @JsonBackReference
    public Collection<BattleEntity> getBattles() {
        return battles;
    }

    public void setBattles(Collection<BattleEntity> battlesById) {
        this.battles = battlesById;
    }

    @OneToMany(mappedBy = "system")
    @JsonManagedReference
    public Collection<PlanetEntity> getPlanets() {
        return planets;
    }

    public void setPlanets(Collection<PlanetEntity> planetsById) {
        this.planets = planetsById;
    }

    @OneToMany(mappedBy = "system")
    @JsonBackReference
    public Collection<ShipEntity> getShips() {
        return ships;
    }

    public void setShips(Collection<ShipEntity> shipsById) {
        this.ships = shipsById;
    }
}

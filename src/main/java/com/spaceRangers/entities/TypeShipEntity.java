package com.spaceRangers.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "type_ship", schema = "s242552", catalog = "course")
public class TypeShipEntity {
    private String name;
    private Collection<ShipEntity> ships;
    private Integer hp;
    private Integer speed;
    private Integer protection;
    private int cost;

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
        TypeShipEntity typeShip = (TypeShipEntity) o;
        return Objects.equals(name, typeShip.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @OneToMany(mappedBy = "typeShip")
    @JsonIgnore
    public Collection<ShipEntity> getShips() {
        return ships;
    }

    public void setShips(Collection<ShipEntity> ships) {
        this.ships = ships;
    }

    @Basic
    @Column(name = "hp")
    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    @Basic
    @Column(name = "speed")
    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    @Basic
    @Column(name = "protection")
    public Integer getProtection() {
        return protection;
    }

    public void setProtection(Integer protection) {
        this.protection = protection;
    }

    @Basic
    @Column(name = "cost")
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}

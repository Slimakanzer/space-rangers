package com.spaceRangers.entities;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Entity
@Table(name = "base", schema = "s242552", catalog = "course")
public class BaseEntity {
    private Integer id;
    private String nameBase;
    private Integer locationBaseX;
    private Integer locationBaseY;
    private UsersEntity user;
    private SystemEntity system;
    private Collection<ShipEntity> ships;

    public BaseEntity(){
        ships = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name_base", nullable = true, length = -1)
    public String getNameBase() {
        return nameBase;
    }

    public void setNameBase(String nameBase) {
        this.nameBase = nameBase;
    }

    @Basic
    @Column(name = "location_base_x", nullable = true)
    public Integer getLocationBaseX() {
        return locationBaseX;
    }

    public void setLocationBaseX(Integer locationBaseX) {
        this.locationBaseX = locationBaseX;
    }

    @Basic
    @Column(name = "location_base_y", nullable = true)
    public Integer getLocationBaseY() {
        return locationBaseY;
    }

    public void setLocationBaseY(Integer locationBaseY) {
        this.locationBaseY = locationBaseY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(nameBase, that.nameBase) &&
                Objects.equals(locationBaseX, that.locationBaseX) &&
                Objects.equals(locationBaseY, that.locationBaseY);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameBase, locationBaseX, locationBaseY);
    }

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @JsonIgnore
    public UsersEntity getUser() {
        return user;
    }

    public void setUser(UsersEntity usersByIdUser) {
        this.user = usersByIdUser;
    }

    @ManyToOne
    @JoinColumn(name = "id_system", referencedColumnName = "id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.None.class)
    public SystemEntity getSystem() {
        return system;
    }

    public void setSystem(SystemEntity systemByIdSystem) {
        this.system = systemByIdSystem;
    }

    @OneToMany(mappedBy = "base")
    @JsonIgnore
    public Collection<ShipEntity> getShips() {
        return ships;
    }

    public void setShips(Collection<ShipEntity> shipsById) {
        this.ships = shipsById;
    }
}

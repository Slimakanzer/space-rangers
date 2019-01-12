package com.spaceRangers.entities;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Entity
@Table(name = "ship", schema = "s242552", catalog = "course")
public class ShipEntity {
    private Integer id;
    private Integer hp;
    private String nameShip;
    private Integer locationShipX;
    private Integer locationShipY;
    private Integer speed;
    private Integer protection;
    private BaseEntity base;
    private SystemEntity system;
    private UsersEntity user;
    private StateShipEntity stateShip;
    private Collection<BattleEntity> battles;
    private TypeShipEntity typeShip;

    public ShipEntity(){
        battles = new HashSet<>();
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
    @Column(name = "hp", nullable = true)
    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    @Basic
    @Column(name = "name_ship", nullable = true, length = 14)
    public String getNameShip() {
        return nameShip;
    }

    public void setNameShip(String nameShip) {
        this.nameShip = nameShip;
    }

    @Basic
    @Column(name = "location_ship_x", nullable = true)
    public Integer getLocationShipX() {
        return locationShipX;
    }

    public void setLocationShipX(Integer locationShipX) {
        this.locationShipX = locationShipX;
    }

    @Basic
    @Column(name = "location_ship_y", nullable = true)
    public Integer getLocationShipY() {
        return locationShipY;
    }

    public void setLocationShipY(Integer locationShipY) {
        this.locationShipY = locationShipY;
    }

    @Basic
    @Column(name = "speed", nullable = true)
    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    @Basic
    @Column(name = "protection", nullable = true)
    public Integer getProtection() {
        return protection;
    }

    public void setProtection(Integer protection) {
        this.protection = protection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShipEntity that = (ShipEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(hp, that.hp) &&
                Objects.equals(nameShip, that.nameShip) &&
                Objects.equals(locationShipX, that.locationShipX) &&
                Objects.equals(locationShipY, that.locationShipY) &&
                Objects.equals(speed, that.speed) &&
                Objects.equals(protection, that.protection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hp, nameShip, locationShipX, locationShipY, speed, protection);
    }


    @ManyToMany(mappedBy = "ships")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    public Collection<BattleEntity> getBattles() {
        return battles;
    }

    public void setBattles(Collection<BattleEntity> shipBattles) {
        this.battles = shipBattles;
    }

    @ManyToOne
    @JoinColumn(name = "id_base", referencedColumnName = "id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.None.class)
    public BaseEntity getBase() {
        return base;
    }

    public void setBase(BaseEntity baseByIdBase) {
        this.base = baseByIdBase;
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

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.None.class)
    public UsersEntity getUser() {
        return user;
    }

    public void setUser(UsersEntity usersByIdUser) {
        this.user = usersByIdUser;
    }

    @ManyToOne
    @JoinColumn(name = "state", referencedColumnName = "id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.None.class)
    public StateShipEntity getStateShip() {
        return stateShip;
    }

    public void setStateShip(StateShipEntity stateShipByState) {
        this.stateShip = stateShipByState;
    }

    @ManyToOne
    @JoinColumn(name = "type_ship", referencedColumnName = "name")
    @JsonIdentityInfo(generator = ObjectIdGenerators.None.class)
    public TypeShipEntity getTypeShip() {
        return typeShip;
    }

    public void setTypeShip(TypeShipEntity typeShipByTypeShip) {
        this.typeShip = typeShipByTypeShip;
    }
}

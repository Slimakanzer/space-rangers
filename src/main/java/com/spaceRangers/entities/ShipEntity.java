package com.spaceRangers.entities;

import javax.persistence.*;

@Entity
@Table(name = "ship", schema = "public", catalog = "course")
public class ShipEntity {
    private int id;
    private Integer hp;
    private String nameShip;
    private Integer idBase;
    private Integer idSystem;
    private Integer idUser;
    private Integer locationShipX;
    private Integer locationShipY;
    private Integer speed;
    private Integer protection;
    private Integer state;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
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
    @Column(name = "id_base", nullable = true)
    public Integer getIdBase() {
        return idBase;
    }

    public void setIdBase(Integer idBase) {
        this.idBase = idBase;
    }

    @Basic
    @Column(name = "id_system", nullable = true)
    public Integer getIdSystem() {
        return idSystem;
    }

    public void setIdSystem(Integer idSystem) {
        this.idSystem = idSystem;
    }

    @Basic
    @Column(name = "id_user", nullable = true)
    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
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

    @Basic
    @Column(name = "state", nullable = true)
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShipEntity that = (ShipEntity) o;

        if (id != that.id) return false;
        if (hp != null ? !hp.equals(that.hp) : that.hp != null) return false;
        if (nameShip != null ? !nameShip.equals(that.nameShip) : that.nameShip != null) return false;
        if (idBase != null ? !idBase.equals(that.idBase) : that.idBase != null) return false;
        if (idSystem != null ? !idSystem.equals(that.idSystem) : that.idSystem != null) return false;
        if (idUser != null ? !idUser.equals(that.idUser) : that.idUser != null) return false;
        if (locationShipX != null ? !locationShipX.equals(that.locationShipX) : that.locationShipX != null)
            return false;
        if (locationShipY != null ? !locationShipY.equals(that.locationShipY) : that.locationShipY != null)
            return false;
        if (speed != null ? !speed.equals(that.speed) : that.speed != null) return false;
        if (protection != null ? !protection.equals(that.protection) : that.protection != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (hp != null ? hp.hashCode() : 0);
        result = 31 * result + (nameShip != null ? nameShip.hashCode() : 0);
        result = 31 * result + (idBase != null ? idBase.hashCode() : 0);
        result = 31 * result + (idSystem != null ? idSystem.hashCode() : 0);
        result = 31 * result + (idUser != null ? idUser.hashCode() : 0);
        result = 31 * result + (locationShipX != null ? locationShipX.hashCode() : 0);
        result = 31 * result + (locationShipY != null ? locationShipY.hashCode() : 0);
        result = 31 * result + (speed != null ? speed.hashCode() : 0);
        result = 31 * result + (protection != null ? protection.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }
}

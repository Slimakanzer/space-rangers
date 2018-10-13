package com.spaceRangers.entities;

import javax.persistence.*;

@Entity
@Table(name = "base", schema = "public", catalog = "course")
public class BaseEntity {
    private int id;
    private String nameBase;
    private Integer idUser;
    private Integer idSystem;
    private Integer locationBaseX;
    private Integer locationBaseY;

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
    @Column(name = "name_base", nullable = true, length = -1)
    public String getNameBase() {
        return nameBase;
    }

    public void setNameBase(String nameBase) {
        this.nameBase = nameBase;
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
    @Column(name = "id_system", nullable = true)
    public Integer getIdSystem() {
        return idSystem;
    }

    public void setIdSystem(Integer idSystem) {
        this.idSystem = idSystem;
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

        if (id != that.id) return false;
        if (nameBase != null ? !nameBase.equals(that.nameBase) : that.nameBase != null) return false;
        if (idUser != null ? !idUser.equals(that.idUser) : that.idUser != null) return false;
        if (idSystem != null ? !idSystem.equals(that.idSystem) : that.idSystem != null) return false;
        if (locationBaseX != null ? !locationBaseX.equals(that.locationBaseX) : that.locationBaseX != null)
            return false;
        if (locationBaseY != null ? !locationBaseY.equals(that.locationBaseY) : that.locationBaseY != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nameBase != null ? nameBase.hashCode() : 0);
        result = 31 * result + (idUser != null ? idUser.hashCode() : 0);
        result = 31 * result + (idSystem != null ? idSystem.hashCode() : 0);
        result = 31 * result + (locationBaseX != null ? locationBaseX.hashCode() : 0);
        result = 31 * result + (locationBaseY != null ? locationBaseY.hashCode() : 0);
        return result;
    }
}

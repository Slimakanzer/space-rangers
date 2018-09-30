package com.spaceRangers.entities;

import javax.persistence.*;

@Entity
@Table(name = "planet", schema = "public", catalog = "course")
public class PlanetEntity {
    private int id;
    private String namePlanet;
    private Integer idSystem;
    private Integer idFraction;
    private Integer locationPlanetX;
    private Integer locationPlanetY;
    private Integer idUser;
    private Integer typeWeather;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name_planet", nullable = true, length = 15)
    public String getNamePlanet() {
        return namePlanet;
    }

    public void setNamePlanet(String namePlanet) {
        this.namePlanet = namePlanet;
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
    @Column(name = "id_fraction", nullable = true)
    public Integer getIdFraction() {
        return idFraction;
    }

    public void setIdFraction(Integer idFraction) {
        this.idFraction = idFraction;
    }

    @Basic
    @Column(name = "location_planet_x", nullable = true)
    public Integer getLocationPlanetX() {
        return locationPlanetX;
    }

    public void setLocationPlanetX(Integer locationPlanetX) {
        this.locationPlanetX = locationPlanetX;
    }

    @Basic
    @Column(name = "location_planet_y", nullable = true)
    public Integer getLocationPlanetY() {
        return locationPlanetY;
    }

    public void setLocationPlanetY(Integer locationPlanetY) {
        this.locationPlanetY = locationPlanetY;
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
    @Column(name = "type_weather", nullable = true)
    public Integer getTypeWeather() {
        return typeWeather;
    }

    public void setTypeWeather(Integer typeWeather) {
        this.typeWeather = typeWeather;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlanetEntity that = (PlanetEntity) o;

        if (id != that.id) return false;
        if (namePlanet != null ? !namePlanet.equals(that.namePlanet) : that.namePlanet != null) return false;
        if (idSystem != null ? !idSystem.equals(that.idSystem) : that.idSystem != null) return false;
        if (idFraction != null ? !idFraction.equals(that.idFraction) : that.idFraction != null) return false;
        if (locationPlanetX != null ? !locationPlanetX.equals(that.locationPlanetX) : that.locationPlanetX != null)
            return false;
        if (locationPlanetY != null ? !locationPlanetY.equals(that.locationPlanetY) : that.locationPlanetY != null)
            return false;
        if (idUser != null ? !idUser.equals(that.idUser) : that.idUser != null) return false;
        if (typeWeather != null ? !typeWeather.equals(that.typeWeather) : that.typeWeather != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (namePlanet != null ? namePlanet.hashCode() : 0);
        result = 31 * result + (idSystem != null ? idSystem.hashCode() : 0);
        result = 31 * result + (idFraction != null ? idFraction.hashCode() : 0);
        result = 31 * result + (locationPlanetX != null ? locationPlanetX.hashCode() : 0);
        result = 31 * result + (locationPlanetY != null ? locationPlanetY.hashCode() : 0);
        result = 31 * result + (idUser != null ? idUser.hashCode() : 0);
        result = 31 * result + (typeWeather != null ? typeWeather.hashCode() : 0);
        return result;
    }
}

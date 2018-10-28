package com.spaceRangers.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "planet", schema = "public", catalog = "course")
public class PlanetEntity {
    private Integer id;
    private String namePlanet;
    private Integer locationPlanetX;
    private Integer locationPlanetY;
    private SystemEntity systemByIdSystem;
    private FractionEntity fractionByIdFraction;
    private UsersEntity usersByIdUser;
    private TypeWeatherEntity typeWeatherByTypeWeather;
    private Collection<ResourceEntity> resourcesById;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlanetEntity that = (PlanetEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(namePlanet, that.namePlanet) &&
                Objects.equals(locationPlanetX, that.locationPlanetX) &&
                Objects.equals(locationPlanetY, that.locationPlanetY);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, namePlanet, locationPlanetX, locationPlanetY);
    }

    @ManyToOne
    @JoinColumn(name = "id_system", referencedColumnName = "id")
    public SystemEntity getSystemByIdSystem() {
        return systemByIdSystem;
    }

    public void setSystemByIdSystem(SystemEntity systemByIdSystem) {
        this.systemByIdSystem = systemByIdSystem;
    }

    @ManyToOne
    @JoinColumn(name = "id_fraction", referencedColumnName = "id")
    public FractionEntity getFractionByIdFraction() {
        return fractionByIdFraction;
    }

    public void setFractionByIdFraction(FractionEntity fractionByIdFraction) {
        this.fractionByIdFraction = fractionByIdFraction;
    }

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    public UsersEntity getUsersByIdUser() {
        return usersByIdUser;
    }

    public void setUsersByIdUser(UsersEntity usersByIdUser) {
        this.usersByIdUser = usersByIdUser;
    }

    @ManyToOne
    @JoinColumn(name = "type_weather", referencedColumnName = "id")
    public TypeWeatherEntity getTypeWeatherByTypeWeather() {
        return typeWeatherByTypeWeather;
    }

    public void setTypeWeatherByTypeWeather(TypeWeatherEntity typeWeatherByTypeWeather) {
        this.typeWeatherByTypeWeather = typeWeatherByTypeWeather;
    }

    @OneToMany(mappedBy = "planetByIdPlanet")
    public Collection<ResourceEntity> getResourcesById() {
        return resourcesById;
    }

    public void setResourcesById(Collection<ResourceEntity> resourcesById) {
        this.resourcesById = resourcesById;
    }
}

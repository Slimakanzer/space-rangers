package com.spaceRangers.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Entity
@Table(name = "planet", schema = "s242552", catalog = "course")
public class PlanetEntity {
    private Integer id;
    private String namePlanet;
    private Integer locationPlanetX;
    private Integer locationPlanetY;
    private SystemEntity system;
    private UsersEntity user;
    private TypeWeatherEntity typeWeather;
    private Collection<ResourceEntity> resources;

    public PlanetEntity(){
        this.resources = new HashSet<>();
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
    @Column(name = "name", nullable = true, length = 15)
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
    @JsonBackReference("planetSystem")
    public SystemEntity getSystem() {
        return system;
    }

    public void setSystem(SystemEntity systemByIdSystem) {
        this.system = systemByIdSystem;
    }

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @JsonBackReference
    public UsersEntity getUser() {
        return user;
    }

    public void setUser(UsersEntity usersByIdUser) {
        this.user = usersByIdUser;
    }

    @ManyToOne
    @JoinColumn(name = "type_weather", referencedColumnName = "id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.None.class)
    public TypeWeatherEntity getTypeWeather() {
        return typeWeather;
    }

    public void setTypeWeather(TypeWeatherEntity typeWeatherByTypeWeather) {
        this.typeWeather = typeWeatherByTypeWeather;
    }

    @OneToMany(mappedBy = "planet")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonManagedReference
    public Collection<ResourceEntity> getResources() {
        return resources;
    }

    public void setResources(Collection<ResourceEntity> resourcesById) {
        this.resources = resourcesById;
    }
}

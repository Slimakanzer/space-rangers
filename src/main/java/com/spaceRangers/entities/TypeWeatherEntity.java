package com.spaceRangers.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "type_weather", schema = "public", catalog = "course")
public class TypeWeatherEntity {
    private Integer id;
    private String name;
    private Collection<PlanetEntity> planetsById;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 30)
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
        TypeWeatherEntity that = (TypeWeatherEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @OneToMany(mappedBy = "typeWeatherByTypeWeather")
    public Collection<PlanetEntity> getPlanetsById() {
        return planetsById;
    }

    public void setPlanetsById(Collection<PlanetEntity> planetsById) {
        this.planetsById = planetsById;
    }
}

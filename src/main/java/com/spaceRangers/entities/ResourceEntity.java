package com.spaceRangers.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "resource", schema = "public", catalog = "course")
public class ResourceEntity {
    private Integer id;
    private String nameResources;
    private Integer count;
    private PlanetEntity planetByIdPlanet;
    private TypeResourcesEntity typeResourcesByType;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name_resources", nullable = true, length = -1)
    public String getNameResources() {
        return nameResources;
    }

    public void setNameResources(String nameResources) {
        this.nameResources = nameResources;
    }

    @Basic
    @Column(name = "count", nullable = true)
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResourceEntity that = (ResourceEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(nameResources, that.nameResources) &&
                Objects.equals(count, that.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameResources, count);
    }

    @ManyToOne
    @JoinColumn(name = "id_planet", referencedColumnName = "id")
    public PlanetEntity getPlanetByIdPlanet() {
        return planetByIdPlanet;
    }

    public void setPlanetByIdPlanet(PlanetEntity planetByIdPlanet) {
        this.planetByIdPlanet = planetByIdPlanet;
    }

    @ManyToOne
    @JoinColumn(name = "type", referencedColumnName = "id")
    public TypeResourcesEntity getTypeResourcesByType() {
        return typeResourcesByType;
    }

    public void setTypeResourcesByType(TypeResourcesEntity typeResourcesByType) {
        this.typeResourcesByType = typeResourcesByType;
    }
}

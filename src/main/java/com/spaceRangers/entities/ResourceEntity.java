package com.spaceRangers.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "resource", schema = "s242552", catalog = "course")
public class ResourceEntity {
    private Integer id;
    private String nameResources;
    private Integer count;
    private PlanetEntity planet;
    private TypeResourcesEntity typeResources;

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
    @Column(name = "name", nullable = true, length = -1)
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
    @JsonBackReference
    public PlanetEntity getPlanet() {
        return planet;
    }

    public void setPlanet(PlanetEntity planetByIdPlanet) {
        this.planet = planetByIdPlanet;
    }

    @ManyToOne
    @JoinColumn(name = "type", referencedColumnName = "id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.None.class)
    public TypeResourcesEntity getTypeResources() {
        return typeResources;
    }

    public void setTypeResources(TypeResourcesEntity typeResourcesByType) {
        this.typeResources = typeResourcesByType;
    }
}

package com.spaceRangers.entities;

import javax.persistence.*;

@Entity
@Table(name = "resource", schema = "public", catalog = "course")
public class ResourceEntity {
    private int id;
    private String nameResources;
    private Integer idPlanet;
    private Integer count;
    private Integer type;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
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
    @Column(name = "id_planet", nullable = true)
    public Integer getIdPlanet() {
        return idPlanet;
    }

    public void setIdPlanet(Integer idPlanet) {
        this.idPlanet = idPlanet;
    }

    @Basic
    @Column(name = "count", nullable = true)
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Basic
    @Column(name = "type", nullable = true)
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResourceEntity that = (ResourceEntity) o;

        if (id != that.id) return false;
        if (nameResources != null ? !nameResources.equals(that.nameResources) : that.nameResources != null)
            return false;
        if (idPlanet != null ? !idPlanet.equals(that.idPlanet) : that.idPlanet != null) return false;
        if (count != null ? !count.equals(that.count) : that.count != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nameResources != null ? nameResources.hashCode() : 0);
        result = 31 * result + (idPlanet != null ? idPlanet.hashCode() : 0);
        result = 31 * result + (count != null ? count.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}

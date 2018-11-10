package com.spaceRangers.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Entity
@Table(name = "type_resources", schema = "public", catalog = "course")
public class TypeResourcesEntity {
    private Integer id;
    private String name;
    private Collection<ResourceEntity> resources;

    public TypeResourcesEntity(){
        resources = new HashSet<>();
    }
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
        TypeResourcesEntity that = (TypeResourcesEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @OneToMany(mappedBy = "typeResources")
    @JsonIgnore
    public Collection<ResourceEntity> getResources() {
        return resources;
    }

    public void setResources(Collection<ResourceEntity> resourcesById) {
        this.resources = resourcesById;
    }
}

package com.spaceRangers.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "fraction", schema = "public", catalog = "course")
public class FractionEntity {
    private Integer id;
    private String nameFraction;
    private PoliticsEntity politicsByIdPolitics;
    private Collection<PlanetEntity> planetsById;
    private Collection<TaskEntity> tasksById;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name_fraction", nullable = false, length = 15)
    public String getNameFraction() {
        return nameFraction;
    }

    public void setNameFraction(String nameFraction) {
        this.nameFraction = nameFraction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FractionEntity that = (FractionEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(nameFraction, that.nameFraction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameFraction);
    }

    @ManyToOne
    @JoinColumn(name = "id_politics", referencedColumnName = "id", nullable = false)
    public PoliticsEntity getPoliticsByIdPolitics() {
        return politicsByIdPolitics;
    }

    public void setPoliticsByIdPolitics(PoliticsEntity politicsByIdPolitics) {
        this.politicsByIdPolitics = politicsByIdPolitics;
    }

    @OneToMany(mappedBy = "fractionByIdFraction")
    public Collection<PlanetEntity> getPlanetsById() {
        return planetsById;
    }

    public void setPlanetsById(Collection<PlanetEntity> planetsById) {
        this.planetsById = planetsById;
    }

    @OneToMany(mappedBy = "fractionByIdFraction")
    public Collection<TaskEntity> getTasksById() {
        return tasksById;
    }

    public void setTasksById(Collection<TaskEntity> tasksById) {
        this.tasksById = tasksById;
    }
}

package com.spaceRangers.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Entity
@Table(name = "fraction", schema = "public", catalog = "course")
public class FractionEntity {
    private Integer id;
    private String nameFraction;
    private PoliticsEntity politics;
    private Collection<TaskEntity> tasks;
    private Collection<UserFractionEntity> usersFraction;

    public FractionEntity(){
        this.tasks = new HashSet<>();
        this.usersFraction = new HashSet<>();
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
    @JsonManagedReference
    public PoliticsEntity getPolitics() {
        return politics;
    }

    public void setPolitics(PoliticsEntity politicsByIdPolitics) {
        this.politics = politicsByIdPolitics;
    }

    @OneToMany(mappedBy = "fraction")
    @JsonManagedReference
    public Collection<TaskEntity> getTasks() {
        return tasks;
    }

    public void setTasks(Collection<TaskEntity> tasksById) {
        this.tasks = tasksById;
    }

    @OneToMany(mappedBy = "fraction")
    @JsonManagedReference
    public Collection<UserFractionEntity> getUsersFraction() {
        return usersFraction;
    }

    public void setUsersFraction(Collection<UserFractionEntity> usersFraction) {
        this.usersFraction = usersFraction;
    }
}

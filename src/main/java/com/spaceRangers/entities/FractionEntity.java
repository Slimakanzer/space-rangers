package com.spaceRangers.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Entity
@Table(name = "fraction")
public class FractionEntity {
    private Integer id;
    private String nameFraction;
    private PoliticsEntity politics;
    private Collection<TaskEntity> tasks;
    private Collection<UserFractionEntity> usersFraction;
    private Integer countPlayer;

    public FractionEntity(){
        this.tasks = new HashSet<>();
        this.usersFraction = new HashSet<>();
    }

    @Basic
    @Column(name = "count_player")
    public Integer getCountPlayer() {
        return countPlayer;
    }

    public void setCountPlayer(Integer countPlayer) {
        this.countPlayer = countPlayer;
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
    @JoinColumn(name = "id_politics", referencedColumnName = "id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.None.class)
    public PoliticsEntity getPolitics(){
        return politics;
    }

    public void setPolitics(PoliticsEntity politics) {
        this.politics = politics;
    }

    @OneToMany(mappedBy = "fraction")
    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    public Collection<TaskEntity> getTasks() {
        return tasks;
    }

    public void setTasks(Collection<TaskEntity> tasksById) {
        this.tasks = tasksById;
    }

    @OneToMany(mappedBy = "fraction")
    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    public Collection<UserFractionEntity> getUsersFraction() {
        return usersFraction;
    }

    public void setUsersFraction(Collection<UserFractionEntity> usersFraction) {
        this.usersFraction = usersFraction;
    }
}

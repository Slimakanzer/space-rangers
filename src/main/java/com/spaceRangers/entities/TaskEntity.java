package com.spaceRangers.entities;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "task")
public class TaskEntity {
    private Integer id;
    private String name;
    private String description;
    private FractionEntity fraction;
    private TypeTaskEntity typeTask;
    private StateTaskEntity stateTask;
    private StatePrivacyEntity statePrivacy;

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
    @Column(name = "name", nullable = true, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description", nullable = true, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskEntity that = (TaskEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }

    @ManyToOne
    @JoinColumn(name = "id_fraction", referencedColumnName = "id", nullable = false)
    public FractionEntity getFraction() {
        return fraction;
    }

    public void setFraction(FractionEntity fractionByIdFraction) {
        this.fraction = fractionByIdFraction;
    }

    @ManyToOne
    @JoinColumn(name = "id_type", referencedColumnName = "id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.None.class)
    public TypeTaskEntity getTypeTask() {
        return typeTask;
    }

    public void setTypeTask(TypeTaskEntity typeTaskByIdType) {
        this.typeTask = typeTaskByIdType;
    }

    @ManyToOne
    @JoinColumn(name = "id_state", referencedColumnName = "id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.None.class)
    public StateTaskEntity getStateTask() {
        return stateTask;
    }

    public void setStateTask(StateTaskEntity stateTaskByIdState) {
        this.stateTask = stateTaskByIdState;
    }

    @ManyToOne
    @JoinColumn(name = "id_privacy", referencedColumnName = "id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.None.class)
    public StatePrivacyEntity getStatePrivacy() {
        return statePrivacy;
    }

    public void setStatePrivacy(StatePrivacyEntity statePrivacyByPrivacy) {
        this.statePrivacy = statePrivacyByPrivacy;
    }
}

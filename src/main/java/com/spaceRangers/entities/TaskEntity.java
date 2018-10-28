package com.spaceRangers.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "task", schema = "public", catalog = "course")
public class TaskEntity {
    private Integer id;
    private String name;
    private String description;
    private Boolean access;
    private FractionEntity fractionByIdFraction;
    private TypeTaskEntity typeTaskByIdType;
    private StateTaskEntity stateTaskByIdState;
    private StatePrivacyEntity statePrivacyByPrivacy;

    @Id
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

    @Basic
    @Column(name = "access", nullable = false)
    public Boolean getAccess() {
        return access;
    }

    public void setAccess(Boolean access) {
        this.access = access;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskEntity that = (TaskEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(access, that.access);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, access);
    }

    @ManyToOne
    @JoinColumn(name = "id_fraction", referencedColumnName = "id", nullable = false)
    public FractionEntity getFractionByIdFraction() {
        return fractionByIdFraction;
    }

    public void setFractionByIdFraction(FractionEntity fractionByIdFraction) {
        this.fractionByIdFraction = fractionByIdFraction;
    }

    @ManyToOne
    @JoinColumn(name = "id_type", referencedColumnName = "id")
    public TypeTaskEntity getTypeTaskByIdType() {
        return typeTaskByIdType;
    }

    public void setTypeTaskByIdType(TypeTaskEntity typeTaskByIdType) {
        this.typeTaskByIdType = typeTaskByIdType;
    }

    @ManyToOne
    @JoinColumn(name = "id_state", referencedColumnName = "id")
    public StateTaskEntity getStateTaskByIdState() {
        return stateTaskByIdState;
    }

    public void setStateTaskByIdState(StateTaskEntity stateTaskByIdState) {
        this.stateTaskByIdState = stateTaskByIdState;
    }

    @ManyToOne
    @JoinColumn(name = "privacy", referencedColumnName = "id")
    public StatePrivacyEntity getStatePrivacyByPrivacy() {
        return statePrivacyByPrivacy;
    }

    public void setStatePrivacyByPrivacy(StatePrivacyEntity statePrivacyByPrivacy) {
        this.statePrivacyByPrivacy = statePrivacyByPrivacy;
    }
}

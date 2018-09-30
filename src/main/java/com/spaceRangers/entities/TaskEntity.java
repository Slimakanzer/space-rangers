package com.spaceRangers.entities;

import javax.persistence.*;

@Entity
@Table(name = "task", schema = "public", catalog = "course")
public class TaskEntity {
    private int id;
    private String name;
    private String description;
    private boolean access;
    private int idFraction;
    private Integer idType;
    private Integer idState;
    private Integer privacy;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
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
    public boolean isAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }

    @Basic
    @Column(name = "id_fraction", nullable = false)
    public int getIdFraction() {
        return idFraction;
    }

    public void setIdFraction(int idFraction) {
        this.idFraction = idFraction;
    }

    @Basic
    @Column(name = "id_type", nullable = true)
    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    @Basic
    @Column(name = "id_state", nullable = true)
    public Integer getIdState() {
        return idState;
    }

    public void setIdState(Integer idState) {
        this.idState = idState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskEntity that = (TaskEntity) o;

        if (id != that.id) return false;
        if (access != that.access) return false;
        if (idFraction != that.idFraction) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (idType != null ? !idType.equals(that.idType) : that.idType != null) return false;
        if (idState != null ? !idState.equals(that.idState) : that.idState != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (access ? 1 : 0);
        result = 31 * result + idFraction;
        result = 31 * result + (idType != null ? idType.hashCode() : 0);
        result = 31 * result + (idState != null ? idState.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "privacy", nullable = true)
    public Integer getPrivacy() {
        return privacy;
    }

    public void setPrivacy(Integer privacy) {
        this.privacy = privacy;
    }
}

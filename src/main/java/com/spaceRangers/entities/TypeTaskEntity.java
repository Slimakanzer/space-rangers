package com.spaceRangers.entities;

import javax.persistence.*;

@Entity
@Table(name = "type_task", schema = "public", catalog = "course")
public class TypeTaskEntity {
    private int id;
    private String nameState;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name_state", nullable = true, length = 30)
    public String getNameState() {
        return nameState;
    }

    public void setNameState(String nameState) {
        this.nameState = nameState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TypeTaskEntity that = (TypeTaskEntity) o;

        if (id != that.id) return false;
        if (nameState != null ? !nameState.equals(that.nameState) : that.nameState != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nameState != null ? nameState.hashCode() : 0);
        return result;
    }
}

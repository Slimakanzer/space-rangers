package com.spaceRangers.entities;

import javax.persistence.*;

@Entity
@Table(name = "system", schema = "public", catalog = "course")
public class SystemEntity {
    private int id;
    private String nameSystem;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name_system", nullable = true, length = 15)
    public String getNameSystem() {
        return nameSystem;
    }

    public void setNameSystem(String nameSystem) {
        this.nameSystem = nameSystem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SystemEntity that = (SystemEntity) o;

        if (id != that.id) return false;
        if (nameSystem != null ? !nameSystem.equals(that.nameSystem) : that.nameSystem != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nameSystem != null ? nameSystem.hashCode() : 0);
        return result;
    }
}

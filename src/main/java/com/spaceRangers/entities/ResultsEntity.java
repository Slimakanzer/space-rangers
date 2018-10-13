package com.spaceRangers.entities;

import javax.persistence.*;

@Entity
@Table(name = "results", schema = "public", catalog = "course")
public class ResultsEntity {
    private int id;
    private Integer idVoting;
    private String name;

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
    @Column(name = "id_voting", nullable = true)
    public Integer getIdVoting() {
        return idVoting;
    }

    public void setIdVoting(Integer idVoting) {
        this.idVoting = idVoting;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 15)
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

        ResultsEntity that = (ResultsEntity) o;

        if (id != that.id) return false;
        if (idVoting != null ? !idVoting.equals(that.idVoting) : that.idVoting != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (idVoting != null ? idVoting.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}

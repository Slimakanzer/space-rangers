package com.spaceRangers.entities;

import javax.persistence.*;

@Entity
@Table(name = "vote", schema = "public", catalog = "course")
public class VoteEntity {
    private int id;
    private Integer idUser;
    private Integer idResult;

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
    @Column(name = "id_user", nullable = true)
    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "id_result", nullable = true)
    public Integer getIdResult() {
        return idResult;
    }

    public void setIdResult(Integer idResult) {
        this.idResult = idResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VoteEntity that = (VoteEntity) o;

        if (id != that.id) return false;
        if (idUser != null ? !idUser.equals(that.idUser) : that.idUser != null) return false;
        if (idResult != null ? !idResult.equals(that.idResult) : that.idResult != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (idUser != null ? idUser.hashCode() : 0);
        result = 31 * result + (idResult != null ? idResult.hashCode() : 0);
        return result;
    }
}

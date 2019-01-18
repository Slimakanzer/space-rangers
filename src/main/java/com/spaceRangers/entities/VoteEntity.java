package com.spaceRangers.entities;

import com.fasterxml.jackson.annotation.*;
import org.omg.CORBA.UserException;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "vote")
@IdClass(VoteEntityPK.class)
public class VoteEntity {
    private Integer idUser;
    private Integer idResult;
    private ResultsEntity results;
    private UsersEntity user;

    @Id
    @Column(name = "id_user", nullable = false)
    public Integer getIdUser(){
        return this.idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    @Id
    @Column(name = "id_result", nullable = false)
    public Integer getIdResult(){
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
        return Objects.equals(idUser, that.idUser) && Objects.equals(idResult, that.idResult);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser*idResult);
    }

    @ManyToOne
    @JoinColumn(name = "id_result", referencedColumnName = "id", updatable = false, insertable = false)
    @JsonBackReference(value = "voteResult")
    public ResultsEntity getResults() {
        return results;
    }

    public void setResults(ResultsEntity results) {
        this.results = results;
    }


    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id", updatable = false, insertable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.None.class)
    public UsersEntity getUser() {
        return user;
    }

    public void setUser(UsersEntity user) {
        this.user = user;
    }
}

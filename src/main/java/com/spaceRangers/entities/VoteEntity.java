package com.spaceRangers.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "vote", schema = "public", catalog = "course")
public class VoteEntity {
    private Integer id;
    private UsersEntity usersByIdUser;
    private ResultsEntity resultsByIdResult;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoteEntity that = (VoteEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    public UsersEntity getUsersByIdUser() {
        return usersByIdUser;
    }

    public void setUsersByIdUser(UsersEntity usersByIdUser) {
        this.usersByIdUser = usersByIdUser;
    }

    @ManyToOne
    @JoinColumn(name = "id_result", referencedColumnName = "id")
    public ResultsEntity getResultsByIdResult() {
        return resultsByIdResult;
    }

    public void setResultsByIdResult(ResultsEntity resultsByIdResult) {
        this.resultsByIdResult = resultsByIdResult;
    }
}

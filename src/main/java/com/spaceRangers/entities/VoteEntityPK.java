package com.spaceRangers.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class VoteEntityPK implements Serializable {

    private Integer idUser;
    private Integer idResult;


    @Column(name = "id_user")
    @Id
    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    @Column(name = "id_result")
    @Id
    public Integer getIdResult() {
        return idResult;
    }

    public void setIdResult(Integer idResult) {
        this.idResult = idResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VoteEntityPK)) return false;
        VoteEntityPK that = (VoteEntityPK) o;
        return Objects.equals(getIdUser(), that.getIdUser()) &&
                Objects.equals(getIdResult(), that.getIdResult());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdUser(), getIdResult());
    }
}

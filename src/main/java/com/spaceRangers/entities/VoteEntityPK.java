package com.spaceRangers.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

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
}

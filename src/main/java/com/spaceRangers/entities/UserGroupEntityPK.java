package com.spaceRangers.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class UserGroupEntityPK implements Serializable {
    private Integer idUser;
    private Integer idGroup;

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }

    @Column(name = "id_user")
    @Id
    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    @Column(name = "id_group")
    @Id
    public Integer getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(Integer idGroup) {
        this.idGroup = idGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserGroupEntityPK that = (UserGroupEntityPK) o;
        return Objects.equals(idUser, that.idUser) &&
                Objects.equals(idGroup, that.idGroup);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idGroup);
    }
}

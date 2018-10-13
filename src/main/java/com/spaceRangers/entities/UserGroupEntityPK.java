package com.spaceRangers.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class UserGroupEntityPK implements Serializable {
    private int idUser;
    private int idGroup;

    @Column(name = "id_user", nullable = false)
    @Id
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Column(name = "id_group", nullable = false)
    @Id
    public int getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserGroupEntityPK that = (UserGroupEntityPK) o;
        return idUser == that.idUser &&
                idGroup == that.idGroup;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idGroup);
    }
}

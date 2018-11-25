package com.spaceRangers.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_group", schema = "s242552", catalog = "course")
@IdClass(UserGroupEntityPK.class)
public class UserGroupEntity {
    private int idUser;
    private int idGroup;

    @Id
    @Column(name = "id_user")
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Id
    @Column(name = "id_group")
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
        UserGroupEntity that = (UserGroupEntity) o;
        return idUser == that.idUser &&
                idGroup == that.idGroup;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idGroup);
    }
}

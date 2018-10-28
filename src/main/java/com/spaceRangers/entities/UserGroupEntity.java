package com.spaceRangers.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_group", schema = "public", catalog = "course")
@IdClass(UserGroupEntityPK.class)
public class UserGroupEntity {
    private Integer idUser;
    private Integer idGroup;
    private UsersEntity usersByIdUser;
    private GroupsEntity groupsByIdGroup;

    @Id
    @Column(name = "id_user", nullable = false)
    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    @Id
    @Column(name = "id_group", nullable = false)
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
        UserGroupEntity that = (UserGroupEntity) o;
        return Objects.equals(idUser, that.idUser) &&
                Objects.equals(idGroup, that.idGroup);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idGroup);
    }
}

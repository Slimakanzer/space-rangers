package com.spaceRangers.entities;

import javax.persistence.*;

@Entity
@Table(name = "group_member", schema = "public", catalog = "course")
@IdClass(GroupMemberEntityPK.class)
public class GroupMemberEntity {
    private int idGroup;
    private int idUser;

    @Id
    @Column(name = "id_group", nullable = false)
    public int getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }

    @Id
    @Column(name = "id_user", nullable = false)
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupMemberEntity that = (GroupMemberEntity) o;

        if (idGroup != that.idGroup) return false;
        if (idUser != that.idUser) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idGroup;
        result = 31 * result + idUser;
        return result;
    }
}

package com.spaceRangers.entities;

import javax.persistence.*;

@Entity
@Table(name = "group_authority", schema = "public", catalog = "course")
public class GroupAuthorityEntity {
    private int id;
    private Integer idGroup;
    private String nameAuthority;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "id_group", nullable = true)
    public Integer getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(Integer idGroup) {
        this.idGroup = idGroup;
    }

    @Basic
    @Column(name = "name_authority", nullable = true, length = -1)
    public String getNameAuthority() {
        return nameAuthority;
    }

    public void setNameAuthority(String nameAuthority) {
        this.nameAuthority = nameAuthority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupAuthorityEntity that = (GroupAuthorityEntity) o;

        if (id != that.id) return false;
        if (idGroup != null ? !idGroup.equals(that.idGroup) : that.idGroup != null) return false;
        if (nameAuthority != null ? !nameAuthority.equals(that.nameAuthority) : that.nameAuthority != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (idGroup != null ? idGroup.hashCode() : 0);
        result = 31 * result + (nameAuthority != null ? nameAuthority.hashCode() : 0);
        return result;
    }
}

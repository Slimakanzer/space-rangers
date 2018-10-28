package com.spaceRangers.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "groups", schema = "public", catalog = "course")
public class GroupsEntity {
    private Integer id;
    private String name;
    private GroupAuthorityEntity groupAuthorityById;
    private Collection<UserGroupEntity> userGroupsById;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupsEntity that = (GroupsEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
    public GroupAuthorityEntity getGroupAuthorityById() {
        return groupAuthorityById;
    }

    public void setGroupAuthorityById(GroupAuthorityEntity groupAuthorityById) {
        this.groupAuthorityById = groupAuthorityById;
    }

}

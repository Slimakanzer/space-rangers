package com.spaceRangers.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "groups", schema = "public", catalog = "course")
public class GroupsEntity {
    private Integer id;
    private String name;
    private GroupAuthorityEntity groupAuthority;
    private Collection<UserAccountEntity> userAccounts;

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

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    public GroupAuthorityEntity getGroupAuthority() {
        return groupAuthority;
    }

    public void setGroupAuthority(GroupAuthorityEntity groupAuthority) {
        this.groupAuthority = groupAuthority;
    }

    @ManyToMany(mappedBy = "groups")
    @JsonBackReference
    public Collection<UserAccountEntity> getUserAccounts(){
        return userAccounts;
    }

    public void setUserAccounts(Collection<UserAccountEntity> userAccounts) {
        this.userAccounts = userAccounts;
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
}

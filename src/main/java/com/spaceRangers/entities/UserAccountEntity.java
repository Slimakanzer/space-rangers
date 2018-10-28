package com.spaceRangers.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_account", schema = "public", catalog = "course")
public class UserAccountEntity {
    private Integer id;
    private String login;
    private String password;
    private UsersEntity usersById;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "login", nullable = true, length = 30)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "password", nullable = true, length = -1)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccountEntity that = (UserAccountEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(login, that.login) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password);
    }

    @OneToOne(mappedBy = "userAccountById")
    public UsersEntity getUsersById() {
        return usersById;
    }

    public void setUsersById(UsersEntity usersById) {
        this.usersById = usersById;
    }
}

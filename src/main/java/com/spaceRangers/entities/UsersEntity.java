package com.spaceRangers.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "public", catalog = "course")
public class UsersEntity {
    private Integer id;
    private String login;
    private String email;
    private String firstName;
    private String lastName;
    private String description;
    private Integer level;
    private Collection<BaseEntity> basesById;
    private Collection<ChatUserEntity> chatUsersById;
    private Collection<MessagesEntity> messagesById;
    private Collection<PlanetEntity> planetsById;
    private Collection<ShipEntity> shipsById;
    private Collection<UserBattleEntity> userBattlesById;
    private Collection<UserFractionEntity> userFractionsById;
    private Collection<UserGroupEntity> userGroupsById;
    private UserAccountEntity userAccountById;
    private StateUserEntity stateUserByIdState;
    private Collection<VoteEntity> votesById;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "login", nullable = true, length = -1)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 30)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "first_name", nullable = true, length = 30)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = true, length = 30)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "description", nullable = true, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "level", nullable = true)
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEntity that = (UsersEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(login, that.login) &&
                Objects.equals(email, that.email) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(description, that.description) &&
                Objects.equals(level, that.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, email, firstName, lastName, description, level);
    }

    @OneToMany(mappedBy = "usersByIdUser")
    public Collection<BaseEntity> getBasesById() {
        return basesById;
    }

    public void setBasesById(Collection<BaseEntity> basesById) {
        this.basesById = basesById;
    }

    @OneToMany(mappedBy = "usersByIdUser")
    public Collection<MessagesEntity> getMessagesById() {
        return messagesById;
    }

    public void setMessagesById(Collection<MessagesEntity> messagesById) {
        this.messagesById = messagesById;
    }

    @OneToMany(mappedBy = "usersByIdUser")
    public Collection<PlanetEntity> getPlanetsById() {
        return planetsById;
    }

    public void setPlanetsById(Collection<PlanetEntity> planetsById) {
        this.planetsById = planetsById;
    }

    @OneToMany(mappedBy = "usersByIdUser")
    public Collection<ShipEntity> getShipsById() {
        return shipsById;
    }

    public void setShipsById(Collection<ShipEntity> shipsById) {
        this.shipsById = shipsById;
    }

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
    public UserAccountEntity getUserAccountById() {
        return userAccountById;
    }

    public void setUserAccountById(UserAccountEntity userAccountById) {
        this.userAccountById = userAccountById;
    }

    @ManyToOne
    @JoinColumn(name = "id_state", referencedColumnName = "id")
    public StateUserEntity getStateUserByIdState() {
        return stateUserByIdState;
    }

    public void setStateUserByIdState(StateUserEntity stateUserByIdState) {
        this.stateUserByIdState = stateUserByIdState;
    }

    @OneToMany(mappedBy = "usersByIdUser")
    public Collection<VoteEntity> getVotesById() {
        return votesById;
    }

    public void setVotesById(Collection<VoteEntity> votesById) {
        this.votesById = votesById;
    }
}

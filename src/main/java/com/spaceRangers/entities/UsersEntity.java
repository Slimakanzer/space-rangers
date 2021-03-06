package com.spaceRangers.entities;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
public class UsersEntity {
    private Integer id;
    private String login;
    private String email;
    private String firstName;
    private String lastName;
    private String description;
    private Integer level;
    private Collection<BaseEntity> bases;
    private Collection<PlanetEntity> planets;
    private Collection<ShipEntity> ships;
    private UserAccountEntity userAccount;
    private StateUserEntity stateUser;
    private Collection<VoteEntity> votes;
    private Collection<ChatEntity> chats;
    private Collection<UserBattleEntity> usersBattle;
    private UserFractionEntity userFraction;
    private Integer coins;

    public UsersEntity(){
        this.bases = new HashSet<>();
        this.planets = new HashSet<>();
        this.ships = new HashSet<>();
        this.votes = new HashSet<>();
        this.chats = new HashSet<>();
        this.usersBattle = new HashSet<>();
    }

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

    @ManyToMany
    @JoinTable(
            name = "chat_user",
            joinColumns = {@JoinColumn(name = "id_user")},
            inverseJoinColumns = {@JoinColumn(name = "id_chat")}
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    public Collection<ChatEntity> getChats(){
        return chats;
    }

    public void setChats(Collection<ChatEntity> chats) {
        this.chats = chats;
    }

    @OneToMany(mappedBy = "user")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    public Collection<VoteEntity> getVotes(){
        return votes;
    }

    public void setVotes(Collection<VoteEntity> votes) {
        this.votes = votes;
    }

    @OneToMany(mappedBy = "user")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    public Collection<BaseEntity> getBases() {
        return bases;
    }

    public void setBases(Collection<BaseEntity> basesById) {
        this.bases = basesById;
    }

    @OneToMany(mappedBy = "user")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    public Collection<PlanetEntity> getPlanets() {
        return planets;
    }

    public void setPlanets(Collection<PlanetEntity> planetsById) {
        this.planets = planetsById;
    }

    @OneToMany(mappedBy = "user")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    public Collection<ShipEntity> getShips() {
        return ships;
    }

    public void setShips(Collection<ShipEntity> shipsById) {
        this.ships = shipsById;
    }

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
    @JsonBackReference(value = "userAccountUser")
    public UserAccountEntity getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccountEntity userAccount) {
        this.userAccount = userAccount;
    }

    @ManyToOne
    @JoinColumn(name = "id_state", referencedColumnName = "id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.None.class)
    public StateUserEntity getStateUser() {
        return stateUser;
    }

    public void setStateUser(StateUserEntity stateUserByIdState) {
        this.stateUser = stateUserByIdState;
    }

    @OneToMany(mappedBy = "user")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    public Collection<UserBattleEntity> getUsersBattle() {
        return usersBattle;
    }

    public void setUsersBattle(Collection<UserBattleEntity> usersBattle) {
        this.usersBattle = usersBattle;
    }

    @Basic
    @Column(name = "coins")
    public Integer getCoins() {
        return coins;
    }

    public void setCoins(Integer coins) {
        this.coins = coins;
    }

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id_user")
    @JsonIgnore
    public UserFractionEntity getUserFraction() {
        return userFraction;
    }

    public void setUserFraction(UserFractionEntity userFraction) {
        this.userFraction = userFraction;
    }
}

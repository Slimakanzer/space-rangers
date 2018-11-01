package com.spaceRangers.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    private Collection<PlanetEntity> planetsById;
    private Collection<ShipEntity> shipsById;
    private UserAccountEntity userAccount;
    private StateUserEntity stateUserByIdState;
    private Collection<VoteEntity> votes;
    private Collection<ChatEntity> chats;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @JsonManagedReference
    public Collection<ChatEntity> getChats(){
        return chats;
    }

    public void setChats(Collection<ChatEntity> chats) {
        this.chats = chats;
    }

    @OneToMany(mappedBy = "usersByIdUser")
    @JsonIgnore
    public Collection<BaseEntity> getBasesById() {
        return basesById;
    }

    public void setBasesById(Collection<BaseEntity> basesById) {
        this.basesById = basesById;
    }

    @OneToMany(mappedBy = "usersByIdUser")
    @JsonIgnore
    public Collection<PlanetEntity> getPlanetsById() {
        return planetsById;
    }

    public void setPlanetsById(Collection<PlanetEntity> planetsById) {
        this.planetsById = planetsById;
    }

    @OneToMany(mappedBy = "usersByIdUser")
    @JsonIgnore
    public Collection<ShipEntity> getShipsById() {
        return shipsById;
    }

    public void setShipsById(Collection<ShipEntity> shipsById) {
        this.shipsById = shipsById;
    }

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    public UserAccountEntity getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccountEntity userAccount) {
        this.userAccount = userAccount;
    }

    @ManyToOne
    @JoinColumn(name = "id_state", referencedColumnName = "id")
    @JsonIgnore
    public StateUserEntity getStateUserByIdState() {
        return stateUserByIdState;
    }

    public void setStateUserByIdState(StateUserEntity stateUserByIdState) {
        this.stateUserByIdState = stateUserByIdState;
    }
}

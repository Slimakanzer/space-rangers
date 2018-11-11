package com.spaceRangers.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Entity
@Table(name = "battle", schema = "public", catalog = "course")
public class BattleEntity {
    private Integer id;
    private String name;
    private Date date;
    private SystemEntity system;
    private Collection<ShipEntity> ships;
    private Collection<UserBattleEntity> userBattles;

    public BattleEntity(){
        this.ships = new HashSet<>();
        this.userBattles = new HashSet<>();
    }

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
    @Column(name = "name", nullable = true, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "date", nullable = true)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BattleEntity that = (BattleEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, date);
    }

    @ManyToOne
    @JoinColumn(name = "id_system", referencedColumnName = "id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.None.class)
    public SystemEntity getSystem() {
        return system;
    }

    public void setSystem(SystemEntity systemByIdSystem) {
        this.system = systemByIdSystem;
    }

    @ManyToMany
    @JoinTable(
            name = "ship_battle",
            joinColumns = {@JoinColumn(name = "id_battle")},
            inverseJoinColumns = {@JoinColumn(name = "id_ship")}
    )
    @JsonIdentityInfo(generator = ObjectIdGenerators.None.class)
    public Collection<ShipEntity> getShips() {
        return ships;
    }

    public void setShips(Collection<ShipEntity> shipBattles) {
        this.ships = shipBattles;
    }


    @OneToMany(mappedBy = "battle")
    @JsonManagedReference
    public Collection<UserBattleEntity> getUserBattles() {
        return userBattles;
    }

    public void setUserBattles(Collection<UserBattleEntity> userBattles) {
        this.userBattles = userBattles;
    }
}

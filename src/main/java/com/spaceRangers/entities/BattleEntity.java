package com.spaceRangers.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "battle", schema = "public", catalog = "course")
public class BattleEntity {
    private Integer id;
    private String name;
    private Date date;
    private SystemEntity systemByIdSystem;
    private Collection<ShipBattleEntity> shipBattlesById;
    private Collection<UserBattleEntity> userBattlesById;

    @Id
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
    public SystemEntity getSystemByIdSystem() {
        return systemByIdSystem;
    }

    public void setSystemByIdSystem(SystemEntity systemByIdSystem) {
        this.systemByIdSystem = systemByIdSystem;
    }
}

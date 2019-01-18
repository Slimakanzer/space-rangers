package com.spaceRangers.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "complain")
public class ComplainEntity {
    private Integer id;
    private Date date;
    private MessagesEntity message;
    private StateComplainEntity stateComplain;

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
        ComplainEntity that = (ComplainEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date);
    }

    @OneToOne
    @JoinColumn(name = "id_message", referencedColumnName = "id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.None.class)
    public MessagesEntity getMessage() {
        return message;
    }

    public void setMessage(MessagesEntity message) {
        this.message = message;
    }

    @ManyToOne
    @JoinColumn(name = "state", referencedColumnName = "name")
    @JsonIdentityInfo(generator = ObjectIdGenerators.None.class)
    public StateComplainEntity getStateComplain() {
        return stateComplain;
    }

    public void setStateComplain(StateComplainEntity stateComplain) {
        this.stateComplain = stateComplain;
    }
}

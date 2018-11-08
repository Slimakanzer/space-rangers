package com.spaceRangers.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Entity
@Table(name = "chat", schema = "public", catalog = "course")
public class ChatEntity {
    private Integer id;
    private String name;
    private Date date;
    private Collection<VotingEntity> votings;
    private Collection<MessagesEntity> messages;
    private Collection<UsersEntity> users;


    public ChatEntity(){
        this.votings = new HashSet<>();
        this.messages = new HashSet<>();
        this.users = new HashSet<>();
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
    @Column(name = "name", nullable = true, length = 30)
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

    @ManyToMany(mappedBy = "chats", fetch = FetchType.EAGER)
    @JsonIgnore
    public Collection<UsersEntity> getUsers(){
        return users;
    }

    public void setUsers(Collection<UsersEntity> users) {
        this.users = users;
    }


    @OneToMany(targetEntity = VotingEntity.class, mappedBy = "chat")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonManagedReference(value = "chatVoting")
    public Collection<VotingEntity> getVotings() {
        return votings;
    }

    public void setVotings(Collection<VotingEntity> votings) {
        this.votings = votings;
    }

    @OneToMany(targetEntity = MessagesEntity.class, mappedBy = "chat")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonManagedReference(value = "chatMessages")
    public Collection<MessagesEntity> getMessages(){return messages;}

    public void setMessages(Collection<MessagesEntity> messages){this.messages = messages;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatEntity that = (ChatEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, date);
    }
}

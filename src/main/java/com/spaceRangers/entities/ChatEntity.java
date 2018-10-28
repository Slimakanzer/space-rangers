package com.spaceRangers.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "chat", schema = "public", catalog = "course")
public class ChatEntity {
    private Integer id;
    private String name;
    private Date date;
    private Collection<ChatUserEntity> chatUsersById;
    private Collection<VotingEntity> votingsById;
    private Collection<MessagesEntity> messagesById;

    @Id
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

    @OneToMany(mappedBy = "chatByIdChat", fetch = FetchType.EAGER)
    public Collection<VotingEntity> getVotingsById() {
        return votingsById;
    }

    public void setVotingsById(Collection<VotingEntity> votingsById) {
        this.votingsById = votingsById;
    }

    @OneToMany(mappedBy = "chatByIdChat", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JsonManagedReference
    public Collection<MessagesEntity> getMessagesById(){return messagesById;}

    public void setMessagesById(Collection<MessagesEntity> messagesById){this.messagesById = messagesById;}
}

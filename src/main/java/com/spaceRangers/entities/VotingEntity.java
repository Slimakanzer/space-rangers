package com.spaceRangers.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "voting", schema = "public", catalog = "course")
public class VotingEntity {
    private Integer id;
    private String message;
    private Collection<ResultsEntity> resultsById;
    private ChatEntity chatByIdChat;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "message", nullable = true, length = -1)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VotingEntity that = (VotingEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message);
    }

    @OneToMany(mappedBy = "votingByIdVoting")
    public Collection<ResultsEntity> getResultsById() {
        return resultsById;
    }

    public void setResultsById(Collection<ResultsEntity> resultsById) {
        this.resultsById = resultsById;
    }

    @ManyToOne
    @JoinColumn(name = "id_chat", referencedColumnName = "id")
    public ChatEntity getChatByIdChat() {
        return chatByIdChat;
    }

    public void setChatByIdChat(ChatEntity chatByIdChat) {
        this.chatByIdChat = chatByIdChat;
    }
}

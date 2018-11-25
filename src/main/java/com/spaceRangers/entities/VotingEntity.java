package com.spaceRangers.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Entity
@Table(name = "voting", schema = "s242552", catalog = "course")
public class VotingEntity {
    private Integer id;
    private String message;
    private Collection<ResultsEntity> results;
    private ChatEntity chat;

    public VotingEntity(){
        this.results = new HashSet<>();
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

    @OneToMany(mappedBy = "voting")
    @JsonManagedReference(value = "votingResult")
    @LazyCollection(LazyCollectionOption.FALSE)
    public Collection<ResultsEntity> getResults() {
        return results;
    }

    public void setResults(Collection<ResultsEntity> results) {
        this.results = results;
    }

    @ManyToOne
    @JoinColumn(name = "id_chat", referencedColumnName = "id")
    @JsonBackReference(value = "chatVoting")
    public ChatEntity getChat() {
        return chat;
    }

    public void setChat(ChatEntity chat) {
        this.chat = chat;
    }
}

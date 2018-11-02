package com.spaceRangers.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Entity
@Table(name = "results", schema = "public", catalog = "course")
public class ResultsEntity {
    private Integer id;
    private String name;
    private VotingEntity voting;
    private Collection<VoteEntity> votes;

    public ResultsEntity(){
        this.votes = new HashSet<>();
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
    @Column(name = "name", nullable = true, length = 15)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultsEntity that = (ResultsEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @ManyToOne
    @JoinColumn(name = "id_voting", referencedColumnName = "id")
    @JsonBackReference
    public VotingEntity getVoting() {
        return voting;
    }

    public void setVoting(VotingEntity voting) {
        this.voting = voting;
    }

    @OneToMany(mappedBy = "results")
    @JsonManagedReference
    public Collection<VoteEntity> getVotes() {
        return votes;
    }

    public void setVotes(Collection<VoteEntity> votes) {
        this.votes = votes;
    }
}

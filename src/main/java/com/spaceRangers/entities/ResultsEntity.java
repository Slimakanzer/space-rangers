package com.spaceRangers.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "results", schema = "public", catalog = "course")
public class ResultsEntity {
    private Integer id;
    private String name;
    private VotingEntity votingByIdVoting;
    private Collection<VoteEntity> votesById;

    @Id
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
    public VotingEntity getVotingByIdVoting() {
        return votingByIdVoting;
    }

    public void setVotingByIdVoting(VotingEntity votingByIdVoting) {
        this.votingByIdVoting = votingByIdVoting;
    }

    @OneToMany(mappedBy = "resultsByIdResult")
    public Collection<VoteEntity> getVotesById() {
        return votesById;
    }

    public void setVotesById(Collection<VoteEntity> votesById) {
        this.votesById = votesById;
    }
}

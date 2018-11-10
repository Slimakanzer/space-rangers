package com.spaceRangers.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Entity
@Table(name = "state_task", schema = "public", catalog = "course")
public class StateTaskEntity {
    private Integer id;
    private String name;
    private Collection<TaskEntity> tasks;

    public StateTaskEntity(){
        this.tasks = new HashSet<>();
    }

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name_state", nullable = true, length = 30)
    public String getName() {
        return name;
    }

    public void setName(String nameState) {
        this.name = nameState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StateTaskEntity that = (StateTaskEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @OneToMany(mappedBy = "stateTask")
    @JsonIgnore
    public Collection<TaskEntity> getTasks() {
        return tasks;
    }

    public void setTasks(Collection<TaskEntity> tasksById) {
        this.tasks = tasksById;
    }
}

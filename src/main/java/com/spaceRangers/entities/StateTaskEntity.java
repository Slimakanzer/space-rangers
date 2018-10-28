package com.spaceRangers.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "state_task", schema = "public", catalog = "course")
public class StateTaskEntity {
    private Integer id;
    private String nameState;
    private Collection<TaskEntity> tasksById;

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
    public String getNameState() {
        return nameState;
    }

    public void setNameState(String nameState) {
        this.nameState = nameState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StateTaskEntity that = (StateTaskEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(nameState, that.nameState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameState);
    }

    @OneToMany(mappedBy = "stateTaskByIdState")
    public Collection<TaskEntity> getTasksById() {
        return tasksById;
    }

    public void setTasksById(Collection<TaskEntity> tasksById) {
        this.tasksById = tasksById;
    }
}

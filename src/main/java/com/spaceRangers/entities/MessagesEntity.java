package com.spaceRangers.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "messages", schema = "s242552", catalog = "course")
public class MessagesEntity {
    private Integer id;
    private Integer idUser;
    private ChatEntity chat;
    private String message;
    private ComplainEntity complain;
    private Integer idChat;

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

    @Basic
    @Column(name = "id_user", nullable = false)
    public  Integer getIdUser(){
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "id_chat", nullable = true)
    public Integer getIdChat(){
        return idChat;
    }

    public void setIdChat(Integer idChat){
        this.idChat = idChat;
    }

    @ManyToOne
    @JoinColumn(name = "id_chat", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonBackReference(value = "chatMessages")
    public ChatEntity getChat(){return chat;}

    public void setChat(ChatEntity chat){this.chat=chat;}

    @OneToOne(mappedBy = "message")
    @JsonIgnore
    public ComplainEntity getComplain() {
        return complain;
    }

    public void setComplain(ComplainEntity complain) {
        this.complain = complain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessagesEntity that = (MessagesEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message);
    }
}

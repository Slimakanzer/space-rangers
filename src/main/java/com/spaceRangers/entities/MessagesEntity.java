package com.spaceRangers.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "messages", schema = "public", catalog = "course")
public class MessagesEntity {
    private Integer id;
    private ChatEntity chatByIdChat;
    private String message;
    private ComplainEntity complainById;
    private UsersEntity usersByIdUser;

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
        MessagesEntity that = (MessagesEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message);
    }


    @OneToOne(mappedBy = "messagesById")
    public ComplainEntity getComplainById() {
        return complainById;
    }

    public void setComplainById(ComplainEntity complainById) {
        this.complainById = complainById;
    }


    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @JsonBackReference
    public UsersEntity getUsersByIdUser() {
        return usersByIdUser;
    }

    public void setUsersByIdUser(UsersEntity usersByIdUser) {
        this.usersByIdUser = usersByIdUser;
    }


    @ManyToOne
    @JoinColumn(name = "id_chat", referencedColumnName = "id")
    @JsonBackReference
    public ChatEntity getChatByIdChat(){return chatByIdChat;}

    public void setChatByIdChat(ChatEntity chatByIdChat){this.chatByIdChat=chatByIdChat;}
}

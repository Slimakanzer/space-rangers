package com.spaceRangers.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "chat_user", schema = "public", catalog = "course")
@IdClass(ChatUserEntityPK.class)
public class ChatUserEntity {
    private Integer idUser;
    private Integer idChat;

    @Id
    @Column(name = "id_user", nullable = false)
    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    @Id
    @Column(name = "id_chat", nullable = false)
    public Integer getIdChat() {
        return idChat;
    }

    public void setIdChat(Integer idChat) {
        this.idChat = idChat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatUserEntity that = (ChatUserEntity) o;
        return Objects.equals(idUser, that.idUser) &&
                Objects.equals(idChat, that.idChat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idChat);
    }
}

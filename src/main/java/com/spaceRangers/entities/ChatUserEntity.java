package com.spaceRangers.entities;

import javax.persistence.*;

@Entity
@Table(name = "chat_user", schema = "public", catalog = "course")
@IdClass(ChatUserEntityPK.class)
public class ChatUserEntity {
    private int idUser;
    private int idChat;

    @Id
    @Column(name = "id_user", nullable = false)
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Id
    @Column(name = "id_chat", nullable = false)
    public int getIdChat() {
        return idChat;
    }

    public void setIdChat(int idChat) {
        this.idChat = idChat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChatUserEntity that = (ChatUserEntity) o;

        if (idUser != that.idUser) return false;
        if (idChat != that.idChat) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUser;
        result = 31 * result + idChat;
        return result;
    }
}

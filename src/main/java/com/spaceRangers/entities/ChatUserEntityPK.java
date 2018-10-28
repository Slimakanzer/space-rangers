package com.spaceRangers.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ChatUserEntityPK implements Serializable {
    private Integer idUser;
    private Integer idChat;

    @Column(name = "id_user", nullable = false)
    @Id
    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    @Column(name = "id_chat", nullable = false)
    @Id
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
        ChatUserEntityPK that = (ChatUserEntityPK) o;
        return Objects.equals(idUser, that.idUser) &&
                Objects.equals(idChat, that.idChat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idChat);
    }
}

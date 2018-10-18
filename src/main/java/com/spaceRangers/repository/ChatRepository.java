package com.spaceRangers.repository;

import com.spaceRangers.entities.ChatEntity;
import com.spaceRangers.entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends CrudRepository<ChatEntity, Integer> {

    @Query(value = "select chat from ChatUserEntity chat_user join ChatEntity chat on(chat_user.idChat = chat.id) where chat_user.idUser = :idUser")
    List<ChatEntity> getChatsOfUser(@Param("idUser")int idUser);

    ChatEntity findChatEntityByName(String name);
}

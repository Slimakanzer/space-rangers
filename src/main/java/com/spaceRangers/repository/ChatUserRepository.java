package com.spaceRangers.repository;

import com.spaceRangers.entities.ChatUserEntity;
import com.spaceRangers.entities.ChatUserEntityPK;
import com.spaceRangers.entities.UserFractionEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatUserRepository extends JpaRepository<ChatUserEntity, ChatUserEntityPK> {

}

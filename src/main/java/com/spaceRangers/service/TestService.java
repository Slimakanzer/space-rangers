package com.spaceRangers.service;

import com.spaceRangers.entities.ChatEntity;

public interface TestService {
    ChatEntity getChat(int id);

    boolean insertChat(ChatEntity chat);
}

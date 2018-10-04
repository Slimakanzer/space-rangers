package com.spaceRangers.service;

import com.spaceRangers.entities.ChatEntity;

import java.util.List;

public interface TestService {
    ChatEntity getChat(int id);

    List<ChatEntity> getListChat();

    ChatEntity insertChat(ChatEntity chat);
}

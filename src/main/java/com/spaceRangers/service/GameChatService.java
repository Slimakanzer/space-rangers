package com.spaceRangers.service;

import com.spaceRangers.entities.ResultsEntity;
import com.spaceRangers.entities.VoteEntity;
import com.spaceRangers.entities.VotingEntity;

import java.util.List;

public interface GameChatService {

    /**
     * Создание голосования
     * @param voting
     * @return
     */
    boolean createVoting(VotingEntity voting);

    /**
     * Получение голосования по id
     * @param idVoting
     * @return
     */
    VotingEntity getVotingById(int idVoting);

    /**
     * Получение списка голосований в данном чате
     * @param idChat
     * @return
     */
    List<VotingEntity> getListVotingByIdChat(int idChat);

    /**
     * Создание варианта выбора в голосовании
     * @param results
     * @return
     */
    boolean createResults(ResultsEntity results);

    /**
     *
     * Удаление варианта выбора в голосовании
     * @param results
     * @return
     */
    boolean dropResults(ResultsEntity results);

    /**
     * Получение всех вариантов выбора данного голосвания
     * @param idVoting
     * @return
     */
    List<ResultsEntity> getListResultsByIdVoting(int idVoting);

    /**
     * Создание голоса (пользователем)
     * @param vote
     * @return
     */
    boolean createVote(VoteEntity vote);

    /**
     * Получение списка голосов у определенного варианта выбора
     * @param idResult
     * @return
     */
    List<VoteEntity> getListVoteByIdResult(int idResult);

}

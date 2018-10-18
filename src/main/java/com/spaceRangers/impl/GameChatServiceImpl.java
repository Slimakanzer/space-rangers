package com.spaceRangers.impl;

import com.spaceRangers.entities.ResultsEntity;
import com.spaceRangers.entities.VoteEntity;
import com.spaceRangers.entities.VotingEntity;
import com.spaceRangers.repository.ResultsRepository;
import com.spaceRangers.repository.VoteRepository;
import com.spaceRangers.repository.VotingRepository;
import com.spaceRangers.service.GameChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("gameChatService")
public class GameChatServiceImpl implements GameChatService {

    @Autowired
    private VotingRepository votingRepository;

    @Autowired
    private ResultsRepository resultsRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Override
    public VotingEntity createVoting(VotingEntity voting) {

        votingRepository.save(voting);
        return voting;
    }

    @Override
    public VotingEntity getVotingById(int idVoting) {
        return votingRepository.findById(idVoting).get();
    }

    @Override
    public List<VotingEntity> getListVotingByIdChat(int idChat) {
        return votingRepository
                .findAll()
                .stream()
                .filter(votingEntity -> votingEntity.getIdChat() == idChat)
                .collect(Collectors.toList());
    }

    @Override
    public boolean createResults(ResultsEntity results) {
        resultsRepository.save(results);
        return true;
    }

    @Override
    public boolean dropResults(ResultsEntity results) {
        resultsRepository.delete(results);
        return true;
    }

    @Override
    public List<ResultsEntity> getListResultsByIdVoting(int idVoting) {
        return resultsRepository
                .findAll()
                .stream()
                .filter(resultsEntity -> resultsEntity.getIdVoting() == idVoting)
                .collect(Collectors.toList());
    }

    @Override
    public boolean createVote(VoteEntity vote) {
        voteRepository.save(vote);
        return true;
    }

    @Override
    public List<VoteEntity> getListVoteByIdResult(int idResult) {
        return voteRepository
                .findAll()
                .stream()
                .filter(voteEntity -> voteEntity.getIdResult() == idResult)
                .collect(Collectors.toList());

    }
}

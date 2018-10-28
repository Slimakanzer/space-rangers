package service;


import com.spaceRangers.entities.*;
import com.spaceRangers.service.ChatService;
import com.spaceRangers.service.GameChatService;
import com.spaceRangers.service.RegistrationService;
import config.TestPersistenceConfig;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@Transactional
@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestPersistenceConfig.class)
@WebAppConfiguration
public class TestGameChatService {

    Logger log = LogManager.getLogger(TestGameChatService.class);

    @Resource
    GameChatService gameChatService;

    @Resource
    ChatService chatService;

    @Resource
    RegistrationService registrationService;

    private UsersEntity usersEntity;

    @Before
    public void getUserEntity(){
        usersEntity = registrationService.createUser("Test", "Test");
    }

    @Test
    public void testCreateResultsInChat(){

        log.info("**************************Test voting functional******************************");
        ChatEntity chatEntity = new ChatEntity();
        chatEntity.setName("TestResource");
        chatEntity.setDate(new Date(1900000000));

        Map map = new HashMap<String, Object>();

        map.put("chat", chatEntity);
        map.put("user", usersEntity);


        ChatEntity chat = chatService.createChat(map);

        log.info("Created chat");

        VotingEntity votingEntity = new VotingEntity();
        votingEntity.setChatByIdChat(chat);
        votingEntity.setMessage("Голосование: металл или дерево");

        votingEntity = gameChatService.createVoting(votingEntity);

        log.info("Created voting");

        ResultsEntity resultsEntity = new ResultsEntity();
        resultsEntity.setVotingByIdVoting(votingEntity);
        resultsEntity.setName("Metall");

        ResultsEntity resultsEntity1 = new ResultsEntity();
        resultsEntity1.setVotingByIdVoting(votingEntity);
        resultsEntity1.setName("Tree");

        gameChatService.createResults(resultsEntity);
        gameChatService.createResults(resultsEntity1);

        log.info("Created results of voing");


        gameChatService.getListResultsByIdVoting(votingEntity.getId())
                .stream()
                .forEach(
                        e->log.info(e.getVotingByIdVoting().getId() + " " + e.getName())
                );

        VoteEntity voteEntity = new VoteEntity();
        voteEntity.setResultsByIdResult(resultsEntity);
        voteEntity.setUsersByIdUser(usersEntity);
        gameChatService.createVote(voteEntity);
        log.info("Created vote for metall");

        gameChatService.getListVoteByIdResult(resultsEntity.getId())
                .stream()
                .forEach(
                        e->log.info(e.getUsersByIdUser().getId())
                );
    }
}

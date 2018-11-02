package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spaceRangers.entities.*;
import com.spaceRangers.service.GameService;
import com.spaceRangers.service.RegistrationService;
import config.TestPersistenceConfig;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.sql.Date;

@Transactional
@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestPersistenceConfig.class)
@WebAppConfiguration
public class TestDatabaseJsonEntityReferences {
    private EntityManager entityManager;
    private ObjectMapper objectMapper;

    Logger log = LogManager.getLogger(TestGameChatService.class);

    @Resource
    private JpaTransactionManager transactionManager;


    @Before
    public void setStarting(){
        this.entityManager = transactionManager.getEntityManagerFactory().createEntityManager();
        this.objectMapper = new ObjectMapper();
    }

    @After
    public void close(){
        entityManager.close();
    }

    public void logJsonObject(Object object){
        try {
            log.info(objectMapper.writeValueAsString(object));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGroupUser(){

        GroupsEntity groupsEntity = entityManager.find(GroupsEntity.class, 1);


        log.info(groupsEntity.getId() + " "+ groupsEntity.getName());

        log.info(groupsEntity.getGroupAuthority().getName());

        logJsonObject(groupsEntity);
        logJsonObject(groupsEntity.getGroupAuthority());

    }

    @Test
    public void testUserAccountGroup(){


        GroupsEntity groupsEntity = entityManager.find(GroupsEntity.class, 1);

       groupsEntity.getUserAccounts()
               .forEach(
                       e->logJsonObject(e)
               );


    }


    @Test
    public void testUserAccountUser(){

        UserAccountEntity userAccountEntity = entityManager.find(UserAccountEntity.class, 129);

        logJsonObject(userAccountEntity);

        logJsonObject(userAccountEntity.getUser());

    }

    @Test
    public void testUserChat(){
        UsersEntity user = entityManager.find(UsersEntity.class, 129);

        logJsonObject(user);

        user.getChats()
                .forEach(e->logJsonObject(e));
    }

    @Test
    public void testChatMessages(){
        ChatEntity chatEntity = entityManager.find(ChatEntity.class, 1);

        logJsonObject(chatEntity);

        chatEntity.getMessages()
                .forEach(
                        e->logJsonObject(e)
                );
    }


    @Test
    public void testComplainMessage(){
//        MessagesEntity messagesEntity = new MessagesEntity();
//        messagesEntity.setIdUser(129);
//        messagesEntity.setMessage("Не работает ");
        ComplainEntity complainEntity = new ComplainEntity();
        complainEntity.setState(false);
        complainEntity.setDate(new Date(12312));
        MessagesEntity messagesEntity = entityManager.find(MessagesEntity.class, 1);
        complainEntity.setMessage(messagesEntity);
        //messagesEntity.setComplain(complainEntity);

        Session session = entityManager.unwrap(Session.class);

        log.info(complainEntity.getMessage());


        session.saveOrUpdate(complainEntity);
//        session.saveOrUpdate(messagesEntity);
//
//
        logJsonObject(messagesEntity);
//
        logJsonObject(complainEntity);


    }

    @Test
    @Transactional
    public void testResultsVote(){

//        ResultsEntity resultsEntity = new ResultsEntity();
//
        VotingEntity votingEntity = entityManager.find(VotingEntity.class, 1);
//
//        resultsEntity.setVoting(votingEntity);
//
//        resultsEntity.setName("Iron name whaat");
//
        Session session = entityManager.unwrap(Session.class);
//
//        session.saveOrUpdate(resultsEntity);

        ResultsEntity resultsEntity = entityManager.find(ResultsEntity.class, 13);

        VoteEntity voteEntity = new VoteEntity();
        voteEntity.setIdUser(63);
        voteEntity.setIdResult(resultsEntity.getId());
        voteEntity.setResults(resultsEntity);

        session.saveOrUpdate(voteEntity);

        VoteEntity voteEntity1 = entityManager.find(VoteEntity.class, voteEntity.getId());

        logJsonObject(voteEntity1.getResults());


        logJsonObject(votingEntity);
    }

    @Test
    public void testSukaBlua(){
        entityManager.getTransaction().begin();

        try{

            Session session = entityManager.unwrap(Session.class);


            UserAccountEntity userAccountEntity = new UserAccountEntity();
            userAccountEntity.setLogin("test");
            userAccountEntity.setPassword("tets");

            UsersEntity usersEntity = new UsersEntity();
            usersEntity.setLevel(1);
            usersEntity.setUserAccount(userAccountEntity);
            userAccountEntity.setUser(usersEntity);

            session.saveOrUpdate(userAccountEntity);
            session.saveOrUpdate(usersEntity);

            logJsonObject(userAccountEntity);

            logJsonObject(usersEntity);



        }finally {
            entityManager.getTransaction().rollback();
        }

    }

}

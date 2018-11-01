package service;


import com.spaceRangers.entities.ChatEntity;
import com.spaceRangers.entities.ChatUserEntity;
import com.spaceRangers.entities.MessagesEntity;
import com.spaceRangers.entities.UsersEntity;
import com.spaceRangers.service.AdministrationService;
import com.spaceRangers.service.ChatService;
import com.spaceRangers.service.RegistrationService;
import config.TestPersistenceConfig;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestPersistenceConfig.class)
@WebAppConfiguration
@Transactional
public class TestChatService {
    Logger log = LogManager.getLogger(TestChatService.class);

    @Resource
    ChatService chatService;

    @Resource
    RegistrationService registrationService;

    @Resource
    AdministrationService administrationService;

    private UsersEntity usersEntity;

    @Before
    public void createUser(){
       usersEntity =  registrationService.createUser("Test", "Test");
    }

    @After
    public void dropUser(){
        administrationService.dropUser(usersEntity);
    }

    @Test
    public void testCreateChat(){
        ChatEntity chatEntity = new ChatEntity();
        chatEntity.setName("Test chat entity");
        chatEntity.setDate(new Date(180000000));

        Map map = new HashMap<String, Object>();
        map.put("chat", chatEntity);
        map.put("user", usersEntity);


        log.info("****************Test create chat entity**************************");
        chatService.createChat(map);


        chatService.getChatsUser(usersEntity.getId())
                .stream()
                .forEach(e-> log.info(e.getName() + " "+e.getId()));
        log.info("Successful");
    }


    @Test
    public void testChatFunctional(){
        log.info("*******************Test chat functional**************************");

        UsersEntity userOne = registrationService.createUser("TestChatOne", "TestChatOne");

        UsersEntity userTwo = registrationService.createUser("TestChatTwo", "TestChatTwo");
        log.info("Created two users");

        ChatEntity chatEntity = new ChatEntity();
        chatEntity.setName("Test chat entity");
        chatEntity.setDate(new Date(180000000));

        Map map = new HashMap<String, Object>();
        map.put("chat", chatEntity);
        map.put("user", userOne);

        ChatEntity chat = chatService.createChat(map);
        log.info("Created chat");

        ChatUserEntity chatUserEntity = new ChatUserEntity();
        chatUserEntity.setIdUser(userTwo.getId());
        chatUserEntity.setIdChat(chat.getId());

        chatService.createChatUser(chatUserEntity);
        log.info("Created chatUser with second user");


        MessagesEntity messagesEntityOne = new MessagesEntity();
        messagesEntityOne.setIdUser(userOne.getId());
        messagesEntityOne.setChat(chat);
        messagesEntityOne.setMessage("Hello!");

        MessagesEntity messagesEntityTwo = new MessagesEntity();
        messagesEntityTwo.setChat(chat);
        messagesEntityTwo.setIdUser(userTwo.getId());
        messagesEntityTwo.setMessage("Hello dude");

        MessagesEntity messagesEntityThree = new MessagesEntity();
        messagesEntityThree.setChat(chat);
        messagesEntityThree.setIdUser(userOne.getId());
        messagesEntityThree.setMessage("How are you");

        chatService.createMessages(messagesEntityOne);
        chatService.createMessages(messagesEntityTwo);
        chatService.createMessages(messagesEntityThree);
        log.info("Created messages");


        chatService.getMessagesOfChat(chat.getId())
                .stream()
                .forEach(e->log.info(e.getChat().getId() +" "+e.getIdUser() +" "+e.getMessage()));
        log.info("Got messages from chat");



    }

}

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
import java.sql.Date;

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestPersistenceConfig.class)
@WebAppConfiguration
public class TestChatService {
    Logger log = LogManager.getLogger(TestRegistrationService.class);

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

        log.info("****************Test create chat entity**************************");
        try {
            chatService.createChat(chatEntity);
            log.info("Successful");
        }catch (Exception e){
            log.info("Failed create");
        }
    }

    @Test
    public void testCreateAndGetChatUsers(){
        ChatUserEntity chatUserEntity = new ChatUserEntity();
        chatUserEntity.setIdChat(11);
        chatUserEntity.setIdUser(usersEntity.getId());

        log.info("****************Test create chat user entity**************************");
        try {
            chatService.createChatUser(chatUserEntity);

            chatService.getChatsUser(usersEntity.getId())
                    .stream()
                    .forEach(e-> log.info(e.getName() + " " + e.getDate()));
            log.info("Successfull");

            chatService.dropChatUser(chatUserEntity);
        }catch (Exception e){
            log.info("Failed");
        }
    }

//    @Test
//    public void testMessagesChat(){
//        ChatEntity chatEntity = new ChatEntity();
//        chatEntity.setName("Test_chat");
//        chatEntity.setDate(new Date(180000000));
//
//        log.info("****************Test messages in chat**************************");
//        try{
//            chatService.createChat(chatEntity);
//            //ChatEntity chat = chatService.getChatByName(chatEntity.getName());
//
//            ChatUserEntity chatUserEntity = new ChatUserEntity();
//            chatUserEntity.setIdUser(usersEntity.getId());
//            chatUserEntity.setIdChat(chat.getId());
//
//            chatService.createChatUser(chatUserEntity);
//
//            MessagesEntity messagesEntity = new MessagesEntity();
//            messagesEntity.setIdChat(chat.getId());
//            messagesEntity.setIdUser(usersEntity.getId());
//            messagesEntity.setMessage("HELLO WORLD");
//
//            chatService.createMessages(messagesEntity);
//
//            chatService.getMessagesOfChat(chat)
//                    .stream()
//                    .forEach(e->log.info(e.getMessage()));
//            log.info("Successfull");
//
//        }catch (Exception e){
//            log.info("Failed");
//            e.printStackTrace();
//        }
//    }

}

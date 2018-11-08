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



        chatService.getChatsUser(usersEntity.getId())
                .stream()
                .forEach(e-> log.info(e.getName() + " "+e.getId()));
        log.info("Successful");
    }


}

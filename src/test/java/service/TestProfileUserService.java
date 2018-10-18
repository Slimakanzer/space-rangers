package service;

import com.spaceRangers.entities.UsersEntity;
import com.spaceRangers.service.AdministrationService;
import com.spaceRangers.service.ProfileUserService;
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

import javax.transaction.Transactional;

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestPersistenceConfig.class)
@WebAppConfiguration
@Transactional
public class TestProfileUserService {

    UsersEntity usersEntity;
    Logger log = LogManager.getLogger(TestProfileUserService.class);

    @Autowired
    RegistrationService registrationService;

    @Autowired
    AdministrationService administrationService;

    @Autowired
    ProfileUserService profileUserService;

    @Before
    public void beforeInstance(){
        usersEntity = registrationService.createUser("Test", "Test");
    }

    @After
    public void afterInstance(){
        administrationService.dropUser(usersEntity);
    }


    @Test
    public void testProfileUser(){
        log.info("********************Testing users profile functional********************");

        log.info("Before update");
        log.info("User id:" + usersEntity.getId());
        usersEntity.setLevel(3);
        usersEntity.setFirstName("KEkovich");
        usersEntity.setLogin("Gamer");
        usersEntity.setDescription("Hello, i'm student from Russia");
        profileUserService.updateUser(usersEntity);

        log.info("After update");
        log.info("User id:" + usersEntity.getId());


        profileUserService.getListUsers()
                .stream()
                .forEach(
                        e->log.info("User id:"+e.getId() + " user lvl:" + e.getLevel())
                );
        log.info("Got all users in system");


        profileUserService
                .getUserListByLevel(1)
                .stream()
                .forEach(
                        e->log.info("User id:"+e.getId()+" user lvl:" + e.getLevel())
                );
        log.info("Got list users by lvl");

        UsersEntity user = profileUserService.getUser("Gamer");
        log.info("Got users by login:" + user.getId() +" "+user.getLogin() + " "+user.getDescription() );
    }
}

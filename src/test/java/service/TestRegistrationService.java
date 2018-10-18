package service;


import com.spaceRangers.entities.StateUserEntity;
import com.spaceRangers.entities.UsersEntity;
import com.spaceRangers.service.AdministrationService;
import com.spaceRangers.service.RegistrationService;
import config.TestPersistenceConfig;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestPersistenceConfig.class)
@WebAppConfiguration
public class TestRegistrationService {

    Logger log = LogManager.getLogger(TestRegistrationService.class);

    @Resource
    private AdministrationService administrationService;

    @Resource
    private RegistrationService registrationService;



    @Test
    @Transactional
    public void testCreateUserAccount() throws Exception {
        log.info("***********************Started test registration user*************************");

        UsersEntity usersEntity = registrationService.createUser("Test", "Test");

        administrationService.dropUser(usersEntity);
        log.info("**********************Success***************************");
    }
}

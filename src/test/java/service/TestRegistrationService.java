package service;


import com.spaceRangers.entities.StateUserEntity;
import com.spaceRangers.entities.UsersEntity;
import com.spaceRangers.service.AdministrationService;
import com.spaceRangers.service.RegistrationService;
import config.TestPersistenceConfig;
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

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestPersistenceConfig.class)
@WebAppConfiguration
public class TestRegistrationService {

    @Resource
    private EntityManagerFactory emf;
    protected EntityManager em;

    @Resource
    private AdministrationService administrationService;

    @Resource
    private RegistrationService registrationService;


    @Before
    public void setUp() throws Exception {
        em = emf.createEntityManager();
    }

    @Test
    public void testCreateUserAccount() throws Exception {
        UsersEntity usersEntity = registrationService.createUser("Test", "Teset");
        System.out.println("Created user_account and users:"+usersEntity.getId() +" "+usersEntity.getLevel());
        administrationService.dropUser(usersEntity);
        System.out.println("Deleted users");
    }

    @Test
    public void testLoginAccount() throws Exception{
        registrationService.createUser("Test", "Teset");

        UsersEntity usersEntity = registrationService.loginUser("Test", "Teset");

        System.out.println("Loggined " + usersEntity.getLevel());

        administrationService.dropUser(usersEntity);
        System.out.println("Deleted users");

        System.out.println("Test validation:");
        UsersEntity usersEntity1 = registrationService.loginUser("sdads", "asdas");
        if (usersEntity1 == null){
            System.out.println("Test passed");
        }else {
            System.out.println("Test failed");
        }
    }
}

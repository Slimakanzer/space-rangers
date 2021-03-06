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
import org.springframework.security.core.userdetails.User;
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
@Transactional
public class TestAdministrationService {

    Logger log = LogManager.getLogger(TestAdministrationService.class);

    private UsersEntity usersEntity;
    @Resource
    private RegistrationService registrationService;

    @Resource
    private AdministrationService administrationService;




    @After
    public void dropUserEntity(){
        administrationService.dropUser(usersEntity);
    }

//    @Test
//    public void testUserGroups(){
//        log.info("*******************Start administrating user group test *****************");
//
//
//        UserGroupEntity userGroupEntity = new UserGroupEntity();
//        userGroupEntity.setIdGroup(1);
//        userGroupEntity.setIdUser(usersEntity.getId());
//
//        log.info("Проверка создания группы пользователя");
//        try {
//            administrationService.createUserGroup(userGroupEntity);
//            log.info("Successful: выполнилось правильно");
//        }catch (Exception e){
//            log.info("ERROR: ошибка при создании гуппы пользователя");
//        }
//
//        log.info("Проверка поиска групп пользователя");
//        try{
//            administrationService.getUsersGroups(usersEntity);
//            log.info("Successful: проверка прошла успешно");
//        }catch (Exception e){
//            log.info("Ошибка при поиске групп пользователя");
//        }
//
//        administrationService.getUsersGroups(usersEntity)
//                .forEach(e-> log.info("id user: "+e.getIdUser()+" id group: "+e.getIdGroup()));
//
//        administrationService.dropUserGroup(userGroupEntity);
//
//
//        log.info("*********************End test******************************");
//    }
}

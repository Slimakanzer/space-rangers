package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spaceRangers.entities.*;
import com.spaceRangers.repository.GroupsRepository;
import com.spaceRangers.repository.UserAccountRepository;
import com.spaceRangers.repository.UserRepository;
import com.spaceRangers.service.GameService;
import com.spaceRangers.service.RegistrationService;
import config.TestPersistenceConfig;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
            log.info(object.getClass().getName()+" "+ objectMapper.writeValueAsString(object));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    UserAccountRepository userAccountRepository;

    @Test
    public void testUserGroups(){
        entityManager.getTransaction().begin();

        try{

            UserAccountEntity userAccountEntity = userAccountRepository.findById(1).get();

            logJsonObject(userAccountEntity);
            userAccountEntity.getGroups().forEach(e-> logJsonObject(e));


        }finally {
            entityManager.getTransaction().rollback();
        }

    }

    @Test
    public void testFractionsFignuas(){

        entityManager.getTransaction().begin();

        try {
            Session session = entityManager.unwrap(Session.class);


            PoliticsEntity politicsEntity = new PoliticsEntity();
            politicsEntity.setId(1);
            politicsEntity.setNamePolitics("testPolitic");

            session.saveOrUpdate(politicsEntity);

            logJsonObject(politicsEntity);


            FractionEntity fractionEntity = new FractionEntity();

            fractionEntity.setNameFraction("Fraction test");
            fractionEntity.setPolitics(politicsEntity);
            politicsEntity.getFractions().add(fractionEntity);

            session.saveOrUpdate(fractionEntity);

            UserFractionEntity userFractionEntity = new UserFractionEntity();

            userFractionEntity.setDate(new Date(123123));
            userFractionEntity.setFraction(fractionEntity);
            fractionEntity.getUsersFraction().add(userFractionEntity);
            userFractionEntity.setUser(entityManager.find(UsersEntity.class, 1));
            userFractionEntity.setIdUser(1);

            session.saveOrUpdate(userFractionEntity);



            TaskEntity taskEntity = new TaskEntity();
            taskEntity.setFraction(fractionEntity);
            fractionEntity.getTasks().add(taskEntity);
            taskEntity.setName("Test task 1");
            taskEntity.setDescription("test task kak bi");
            taskEntity.setStatePrivacy(entityManager.find(StatePrivacyEntity.class, 1));
            taskEntity.setStateTask(entityManager.find(StateTaskEntity.class, 1));
            taskEntity.setTypeTask(entityManager.find(TypeTaskEntity.class, 1));

            session.saveOrUpdate(taskEntity);

            TaskEntity taskEntity1 = new TaskEntity();
            taskEntity1.setFraction(fractionEntity);
            fractionEntity.getTasks().add(taskEntity1);
            taskEntity1.setTypeTask(entityManager.find(TypeTaskEntity.class, 2));
            taskEntity1.setStateTask(entityManager.find(StateTaskEntity.class, 2));
            taskEntity1.setStatePrivacy(entityManager.find(StatePrivacyEntity.class, 1));
            taskEntity1.setDescription("test task two kak by");
            taskEntity1.setName("Test task 2");

            session.saveOrUpdate(taskEntity1);


            session.saveOrUpdate(fractionEntity);

            FractionEntity finded = entityManager.find(FractionEntity.class, fractionEntity.getId());

            logJsonObject(finded);

            finded.getTasks().forEach(e->logJsonObject(e));

        }finally {
            entityManager.getTransaction().rollback();
        }

    }

    @Test
    public void testShips(){

        Session session = entityManager.unwrap(Session.class);

        entityManager.getTransaction().begin();
        try{
            SystemEntity system = entityManager.find(SystemEntity.class, 1);
            StateShipEntity stateShipEntity = entityManager.find(StateShipEntity.class, 1);
            StateShipEntity stateShipEntity1 = entityManager.find(StateShipEntity.class, 2);

            UsersEntity usersEntity = entityManager.find(UsersEntity.class, 1);

            StateUserBattleEntity stateUserBattleEntity = entityManager.find(StateUserBattleEntity.class, 1);
            StateUserBattleEntity stateUserBattleEntity1 = entityManager.find(StateUserBattleEntity.class, 2);


            BaseEntity baseEntity = new BaseEntity();
            baseEntity.setLocationBaseX(1);
            baseEntity.setLocationBaseY(1);
            baseEntity.setNameBase("test base");
            baseEntity.setUser(usersEntity);
            usersEntity.getBases().add(baseEntity);
            baseEntity.setSystem(system);

            session.saveOrUpdate(baseEntity);

            ShipEntity shipEntity= new ShipEntity();
            shipEntity.setHp(450);
            shipEntity.setLocationShipX(1);
            shipEntity.setLocationShipY(1);
            shipEntity.setNameShip("Test ship 1");
            shipEntity.setProtection(15);
            shipEntity.setSystem(system);
            shipEntity.setBase(baseEntity);
            baseEntity.getShips().add(shipEntity);
            shipEntity.setStateShip(stateShipEntity);

            session.saveOrUpdate(shipEntity);

            ShipEntity shipEntity1 = new ShipEntity();
            shipEntity1.setHp(550);
            shipEntity1.setLocationShipX(1);
            shipEntity1.setLocationShipY(1);
            shipEntity1.setNameShip("Test ship 2");
            shipEntity1.setProtection(40);
            shipEntity1.setSystem(system);
            shipEntity1.setBase(baseEntity);
            baseEntity.getShips().add(shipEntity1);
            shipEntity1.setStateShip(stateShipEntity1);

            session.saveOrUpdate(shipEntity1);

            BattleEntity battleEntity = new BattleEntity();
            battleEntity.setSystem(system);
            battleEntity.setName("Test battle");

            session.saveOrUpdate(battleEntity);

            UserBattleEntity userBattleEntity = new UserBattleEntity();
            userBattleEntity.setDate(new Date(1231231));
            userBattleEntity.setIdBattle(battleEntity.getId());
            userBattleEntity.setBattle(battleEntity);
            battleEntity.getUserBattles().add(userBattleEntity);
            userBattleEntity.setIdUser(1);
            userBattleEntity.setUser(usersEntity);
            userBattleEntity.setStateUserBattle(stateUserBattleEntity);

            session.saveOrUpdate(userBattleEntity);




            ShipEntity ship = entityManager.find(ShipEntity.class, shipEntity.getId());
            BattleEntity battle = entityManager.find(BattleEntity.class, battleEntity.getId());

            logJsonObject(ship);
            logJsonObject(battle);

        }finally {
            entityManager.getTransaction().rollback();
        }

    }

    @Test
    public void testPlanets(){
        entityManager.getTransaction().begin();

        try{
            Session session = entityManager.unwrap(Session.class);

            UsersEntity usersEntity = entityManager.find(UsersEntity.class, 1);

            TypeResourcesEntity typeResourcesEntity = entityManager.find(TypeResourcesEntity.class, 1);
            TypeWeatherEntity typeWeatherEntity = entityManager.find(TypeWeatherEntity.class, 1);

            SystemEntity systemEntity = entityManager.find(SystemEntity.class, 1);


//            systemEntity.getPlanets()
//                    .forEach(
//                            e->logJsonObject(e)
//                    );

            PlanetEntity planetEntity = new PlanetEntity();
            planetEntity.setLocationPlanetX(123);
            planetEntity.setLocationPlanetY(12314);
            planetEntity.setNamePlanet("Test planet");
            planetEntity.setSystem(systemEntity);
            planetEntity.setTypeWeather(typeWeatherEntity);
            systemEntity.getPlanets().add(planetEntity);
            planetEntity.setUser(usersEntity);

            session.saveOrUpdate(planetEntity);

            session.refresh(systemEntity);

            ResourceEntity resourceEntity = new ResourceEntity();
            resourceEntity.setNameResources("Iron mountains");
            resourceEntity.setPlanet(planetEntity);
            planetEntity.getResources().add(resourceEntity);
            resourceEntity.setTypeResources(typeResourcesEntity);

            session.saveOrUpdate(resourceEntity);

            ResourceEntity resourceEntity1 = new ResourceEntity();
            resourceEntity1.setTypeResources(typeResourcesEntity);
            resourceEntity1.setPlanet(planetEntity);
            planetEntity.getResources().add(resourceEntity1);
            resourceEntity1.setCount(123);
            resourceEntity1.setNameResources("Test resourse 2");

            session.saveOrUpdate(resourceEntity1);

            SystemEntity systemEntity1 = entityManager.find(SystemEntity.class, 1);

            logJsonObject(systemEntity1);

            systemEntity1.getPlanets()
                    .forEach(e->logJsonObject(e));

            System.out.println(systemEntity1.getPlanets().size());

        }finally {
            entityManager.getTransaction().rollback();
        }
    }


    @Test
    public void completeTest(){


    }

}

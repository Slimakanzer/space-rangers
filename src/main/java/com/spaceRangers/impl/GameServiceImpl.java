package com.spaceRangers.impl;

import com.spaceRangers.config.websocket.exceptions.NotEnoughMoneyException;
import com.spaceRangers.config.websocket.exceptions.TypeError;
import com.spaceRangers.entities.*;
import com.spaceRangers.repository.*;
import com.spaceRangers.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service("gameService")
public class GameServiceImpl implements GameService {

    private final PlanetRepository planetRepository;

    private final ResourceRepository resourceRepository;

    private final BaseRepository baseRepository;

    private final ShipRepository shipRepository;

    private final UserRepository userRepository;


    @Autowired
    private TypeShipRepository typeShipRepository;

    @Autowired
    StateShipRepository stateShipRepository;

    @Autowired
    SystemRepository systemRepository;

    @Autowired
    public GameServiceImpl(PlanetRepository planetRepository, ResourceRepository resourceRepository, BaseRepository baseRepository, ShipRepository shipRepository, UserRepository userRepository) {
        this.planetRepository = planetRepository;
        this.resourceRepository = resourceRepository;
        this.baseRepository = baseRepository;
        this.shipRepository = shipRepository;
        this.userRepository = userRepository;
    }

    /**
     * Получение планеты
     *
     * @param idPlanet
     * @return
     */
    @Override
    public PlanetEntity getPlanet(int idPlanet) {
        return planetRepository.findById(idPlanet).get();

    }

    /**
     * Получение планеты по названию
     *
     * @param name
     * @return
     */
    @Override
    public PlanetEntity getPlanet(String name) {
        return planetRepository.findPlanetEntityByNamePlanet(name);
    }

    /**
     * Обновление планеты
     * например, когда кто-то её завоёвывает
     *
     * @param planet
     * @return
     */
    @Override
    @Transactional
    public PlanetEntity updatePlanet(PlanetEntity planet) {
        planetRepository.save(planet);
        return planet;
    }

    /**
     * Получение списка ресурсов планеты
     *
     * @param idPlanet
     * @return
     */
    @Override
    public List<ResourceEntity> getListResourceByIdPlanet(int idPlanet) {
        // TODO в репозиторий
        return resourceRepository
                .findAll()
                .stream()
                .filter(resourceEntity -> resourceEntity.getPlanet().getId() == idPlanet)
                .collect(Collectors.toList());
    }

    /**
     * Получение спика ресурсов пользователя
     *
     * @param idUser
     * @return
     */
    @Override
    public List<ResourceEntity> getListResourceUsers(int idUser) {

        return resourceRepository.getResourceOfUser(idUser);
    }

    /**
     * Удаление ресурса
     *
     * @param resource
     * @return
     */
    @Override
    public boolean removeResource(ResourceEntity resource) {
        resourceRepository.delete(resource);
        return true;
    }

    /**
     * Получение сипска баз пользователя
     *
     * @param idUser
     * @return
     */
    @Override
    public List<BaseEntity> getListBaseUsers(int idUser) {
        // TODO в репозиторий
        return baseRepository
                .findAll()
                .stream()
                .filter(baseEntity -> baseEntity.getUser().getId() == idUser)
                .collect(Collectors.toList());
    }

    /**
     * Создание базы
     *
     * @param base
     * @return
     */
    @Override
    @Transactional
    public BaseEntity createBase(BaseEntity base) {
        UsersEntity usersEntity = base.getUser();
        usersEntity.setCoins(usersEntity.getCoins() - 1500);
        userRepository.save(usersEntity);
        base.setId(null);
        base.setSystem(systemRepository.findById(1).get());
        base.setLocationBaseX((int) Math.round(Math.random()*20000-10000));
        base.setLocationBaseY((int) Math.round(Math.random()*20000-10000));
        base.setLocationBaseZ((int) Math.round(Math.random()*20000-10000));
        baseRepository.save(base);
        return base;
    }

    /**
     * Обновление базы
     * может смениться пользователь
     *
     * @param base
     * @return
     */
    @Override
    @Transactional
    public BaseEntity updateBase(BaseEntity base) {
        baseRepository.save(base);
        return base;
    }

    /**
     * Создание корабля
     *
     * @param ship
     * @return
     */
    @Override
    @Transactional
    public ShipEntity createShip(ShipEntity ship) throws Exception {

        if(ship.getTypeShip() == null || ship.getBase() == null){
            throw new NoSuchFieldException("Null field");
        }

        if (ship.getUser().getCoins() < ship.getTypeShip().getCost()) throw new NotEnoughMoneyException("You don't have money", TypeError.NOT_ENOUGH_MONEY_CREATE_SHIP);


        ship.setStateShip(stateShipRepository.findById(2).get());
        ship.getUser().setCoins(ship.getUser().getCoins() - ship.getTypeShip().getCost());
        ship.setLocationShipX(ship.getBase().getLocationBaseX()+70);
        ship.setLocationShipY(ship.getBase().getLocationBaseY()+70);
        ship.setLocationShipZ(ship.getBase().getLocationBaseZ()+70);
        ship.setRotationShipX(0f);
        ship.setRotationShipY(0f);
        ship.setRotationShipZ(0f);

        userRepository.save(ship.getUser());

        ship.setSystem(ship.getBase().getSystem());
        ship.setHp(ship.getTypeShip().getHp());
        ship.setSpeed(ship.getTypeShip().getSpeed());
        ship.setProtection(ship.getTypeShip().getProtection());

        shipRepository.save(ship);
        return ship;
    }

    @Override
    public ShipEntity powerupShip(ShipEntity ship) throws Exception {
        if(ship.getUser().getCoins() < 20) throw new NotEnoughMoneyException("You don't have money", TypeError.NOT_ENOUGH_MONEY_POWERUP);

        ship.getUser().setCoins(ship.getUser().getCoins() - 20);
        userRepository.save(ship.getUser());

        return shipRepository.save(ship);
    }

    /**
     * Обновление  корабля
     *
     * @param ship
     * @return
     */
    @Override
    @Transactional
    public ShipEntity updateShip(ShipEntity ship) {
        shipRepository.save(ship);
        return ship;
    }

    /**
     * Получение корабля по id
     *
     * @return
     */
    @Override
    public ShipEntity getShip(UsersEntity user, int id) {
        List<ShipEntity> list = user.getShips()
                .stream()
                .filter(e->e.getId().equals(id))
                .collect(Collectors.toList());

        if(list.size()==0) return null;
        return list.get(0);

    }


    /**
     * Получение списка кораблей, которые привязаны к базе
     *
     * @param idBase
     * @return
     */
    @Override
    public List<ShipEntity> getListShipByIdBase(int idBase) {
        return shipRepository.findShipEntitiesByBase( baseRepository.findById(idBase).get());
    }

    /**
     * Получение списка всех кораблей пользователя
     *
     * @param idUser
     * @return
     */
    @Override
    public List<ShipEntity> getListShipByIdUser(int idUser) {

        return shipRepository.findShipEntitiesByUser(userRepository.findById(idUser).get());
    }

    /**
     * Создание планеты
     *
     * @param planet
     * @return
     */
    @Override
    @Transactional
    public PlanetEntity createPlanet(PlanetEntity planet) {
        planetRepository.save(planet);
        return planet;
    }

    /**
     * Содание ресурса
     *
     * @param resource
     * @return
     */
    @Override
    @Transactional
    public ResourceEntity createResource(ResourceEntity resource) {
        resourceRepository.saveAndFlush(resource);
        return resource;
    }

    @Override
    public BaseEntity getUserBase(UsersEntity user, int idBase) throws NoSuchElementException {
        BaseEntity baseEntity  =user
                .getBases()
                .stream()
                .filter(e->e.getId().equals(idBase))
                .findFirst().get();

        return baseEntity;
    }

    /**
     * Удаление корабля
     *
     * @param ship
     * @return
     */
    @Override
    public boolean dropShip(ShipEntity ship) {
        shipRepository.delete(ship);
        return true;
    }

    public List<ShipEntity> getAllShips(){
        List<ShipEntity> list = new ArrayList<>();
        shipRepository.findAll().iterator()
                .forEachRemaining(list::add);
        return list;
    }

    public ShipEntity getShip(int id){
        return shipRepository.findById(id).get();
    }

    public List<BaseEntity> getAllBases(){
        List<BaseEntity> list = new ArrayList<>();
        baseRepository.findAll().iterator()
                .forEachRemaining(list::add);
        return list;
    }

    public BaseEntity getBase(int id){
        return baseRepository.findById(id).get();
    }

    public Collection<SystemEntity> getAllSystems(){
        Collection<SystemEntity> list = new ArrayList<>();

        systemRepository.findAll().iterator()
                .forEachRemaining(list::add);
        return list;
    }

    public SystemEntity getSystem(String name){
        return systemRepository.findSystemEntityByNameSystem(name);
    }

    public SystemEntity getSystem(int id){
        return systemRepository.findById(id).get();
    }

    public ResourceEntity getResource(int id){
        return resourceRepository.findById(id).get();
    }

    @Transactional
    public ResourceEntity updateResource(ResourceEntity resourceEntity){
        resourceRepository.save(resourceEntity);
        return resourceEntity;
    }

    @Override
    public Collection<TypeShipEntity> getShipTypes() {
        return typeShipRepository.findAll();
    }
}

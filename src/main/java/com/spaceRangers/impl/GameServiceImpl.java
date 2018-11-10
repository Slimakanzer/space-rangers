package com.spaceRangers.impl;

import com.spaceRangers.entities.*;
import com.spaceRangers.repository.*;
import com.spaceRangers.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service("gameService")
public class GameServiceImpl implements GameService {

    private final PlanetRepository planetRepository;

    private final ResourceRepository resourceRepository;

    private final BaseRepository baseRepository;

    private final ShipRepository shipRepository;

    private final UserRepository userRepository;

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
        base.setId(null);
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
    public ShipEntity createShip(ShipEntity ship) {
        shipRepository.save(ship);
        return ship;
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
    public ResourceEntity createResource(ResourceEntity resource) {
        resourceRepository.saveAndFlush(resource);
        return resource;
    }

    @Override
    public BaseEntity getUserBase(UsersEntity user, int idBase){
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
}

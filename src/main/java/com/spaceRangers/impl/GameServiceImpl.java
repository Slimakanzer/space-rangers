package com.spaceRangers.impl;

import com.spaceRangers.entities.BaseEntity;
import com.spaceRangers.entities.PlanetEntity;
import com.spaceRangers.entities.ResourceEntity;
import com.spaceRangers.entities.ShipEntity;
import com.spaceRangers.repository.*;
import com.spaceRangers.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                .filter(resourceEntity -> resourceEntity.getPlanetByIdPlanet().getId() == idPlanet)
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
                .filter(baseEntity -> baseEntity.getUsersByIdUser().getId() == idUser)
                .collect(Collectors.toList());
    }

    /**
     * Создание базы
     *
     * @param base
     * @return
     */
    @Override
    public BaseEntity createBase(BaseEntity base) {
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
    public ShipEntity updateShip(ShipEntity ship) {
        shipRepository.save(ship);
        return ship;
    }

    /**
     * Получение корабля по id
     *
     * @param idShip
     * @return
     */
    @Override
    public ShipEntity getShipById(int idShip) {
        return shipRepository.findById(idShip).get();
    }


    /**
     * Получение списка кораблей, которые привязаны к базе
     *
     * @param idBase
     * @return
     */
    @Override
    public List<ShipEntity> getListShipByIdBase(int idBase) {
        return shipRepository.findShipEntitiesByBaseByIdBase( baseRepository.findById(idBase).get());
    }

    /**
     * Получение списка всех кораблей пользователя
     *
     * @param idUser
     * @return
     */
    @Override
    public List<ShipEntity> getListShipByIdUser(int idUser) {

        return shipRepository.findShipEntitiesByUsersByIdUser(userRepository.findById(idUser).get());
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

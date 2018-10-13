package com.spaceRangers.impl;

import com.spaceRangers.entities.BaseEntity;
import com.spaceRangers.entities.PlanetEntity;
import com.spaceRangers.entities.ResourceEntity;
import com.spaceRangers.entities.ShipEntity;
import com.spaceRangers.repository.BaseRepository;
import com.spaceRangers.repository.PlanetRepository;
import com.spaceRangers.repository.ResourceRepository;
import com.spaceRangers.repository.ShipRepository;
import com.spaceRangers.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("gameService")
public class GameServiceImpl implements GameService {

    @Autowired
    PlanetRepository planetRepository;

    @Autowired
    ResourceRepository resourceRepository;

    @Autowired
    BaseRepository baseRepository;

    @Autowired
    ShipRepository shipRepository;
    /**
     * Получение планеты
     *
     * @param idPlanet
     * @return
     */
    @Override
    public PlanetEntity getPlanetById(int idPlanet) {
        return planetRepository.findById(idPlanet).get();

    }

    /**
     * Получение планеты по названию
     *
     * @param name
     * @return
     */
    @Override
    public PlanetEntity getPlanetIdByName(String name) {
        // TODO в репозиторий
        return null;
    }

    /**
     * Обновление планеты
     * например, когда кто-то её завоёвывает
     *
     * @param planet
     * @return
     */
    @Override
    public boolean updatePlanet(PlanetEntity planet) {
        planetRepository.save(planet);
        return true;
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
                .filter(resourceEntity -> resourceEntity.getIdPlanet() == idPlanet)
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
        return baseRepository
                .findAll()
                .stream()
                .filter(baseEntity -> baseEntity.getIdUser() == idUser)
                .collect(Collectors.toList());
    }

    /**
     * Создание базы
     *
     * @param base
     * @return
     */
    @Override
    public boolean createBase(BaseEntity base) {
        baseRepository.save(base);
        return true;
    }

    /**
     * Обновление базы
     * может смениться пользователь
     *
     * @param base
     * @return
     */
    @Override
    public boolean updateBase(BaseEntity base) {
        baseRepository.save(base);
        return true;
    }

    /**
     * Создание корабля
     *
     * @param ship
     * @return
     */
    @Override
    public boolean createShip(ShipEntity ship) {
        shipRepository.save(ship);
        return true;
    }

    /**
     * Обновление  корабля
     *
     * @param ship
     * @return
     */
    @Override
    public boolean updateShip(ShipEntity ship) {
        shipRepository.save(ship);
        return true;
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
        // TODO перенести в репозиторий
        return null;
    }

    /**
     * Получение списка всех кораблей пользователя
     *
     * @param idUser
     * @return
     */
    @Override
    public List<ShipEntity> getListShipByIdUser(int idUser) {
        // TODO перенести в репозиторий
        return null;
    }

    /**
     * Получение кораблей пользователя в определенном состоянии
     *
     * @param idUser
     * @param idStateShip
     * @return
     */
    @Override
    public List<ShipEntity> getListShypByIdUserAndIdStateShip(int idUser, int idStateShip) {
        //TODO в репозиторий
        return null;
    }
}

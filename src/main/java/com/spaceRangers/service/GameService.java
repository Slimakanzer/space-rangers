package com.spaceRangers.service;

import com.spaceRangers.entities.*;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

public interface GameService {

    /**
     * Создание планеты
     * @param planet
     * @return
     */
    PlanetEntity createPlanet(PlanetEntity planet);

    /**
     * Получение планеты
     * @param idPlanet
     * @return
     */
    PlanetEntity getPlanet(int idPlanet);

    /**
     * Получение планеты по названию
     * @param name
     * @return
     */
    PlanetEntity getPlanet(String name);

    /**
     * Обновление планеты
     * например, когда кто-то её завоёвывает
     * @param planet
     * @return
     */
    PlanetEntity updatePlanet(PlanetEntity planet);


    /**
     * Содание ресурса
     * @param resource
     * @return
     */
    ResourceEntity createResource(ResourceEntity resource);

    /**
     * Получение списка ресурсов планеты
     * @param idPlanet
     * @return
     */
    List<ResourceEntity> getListResourceByIdPlanet(int idPlanet);

    /**
     * Получение спика ресурсов пользователя
     * @param idUser
     * @return
     */
    List<ResourceEntity> getListResourceUsers(int idUser);

    /**
     * Удаление ресурса
     * @param resource
     * @return
     */
    boolean removeResource(ResourceEntity resource);

    /**
     * Получение сипска баз пользователя
     * @param idUser
     * @return
     */
    List<BaseEntity> getListBaseUsers(int idUser);

    /**
     * Создание базы
     * @param base
     * @return
     */
    BaseEntity createBase(BaseEntity base);

    /**
     * Обновление базы
     * может смениться пользователь
     * @param base
     * @return
     */
    BaseEntity updateBase(BaseEntity base);

    /**
     * Получение корабля по id
     * @param idShip
     * @return
     */
    ShipEntity getShip(UsersEntity user, int idShip);

    /**
     * Создание корабля
     * @param ship
     * @return
     */
    ShipEntity createShip(ShipEntity ship) throws Exception;

    @Transactional
    ShipEntity powerupShip(ShipEntity ship) throws Exception;

    /**
     * Обновление  корабля
     * @param ship
     * @return
     */
    ShipEntity updateShip(ShipEntity ship);

    /**
     * Удаление корабля
     * @param ship
     * @return
     */
    boolean dropShip(ShipEntity ship);


    /**
     * Получение списка кораблей, которые привязаны к базе
     * @param idBase
     * @return
     */
    List<ShipEntity> getListShipByIdBase(int idBase);


    /**
     * Получение списка всех кораблей пользователя
     * @param idUser
     * @return
     */
    List<ShipEntity> getListShipByIdUser(int idUser);

    /**
     * Получение баз пользователя
     * @param user
     * @param idBase
     * @return
     */
    BaseEntity getUserBase(UsersEntity user, int idBase);

    /**
     * Получение всех кораблей
     * @return
     */
    List<ShipEntity> getAllShips();

    /**
     * Получение корабля по id
     * @param id
     * @return
     */
    ShipEntity getShip(int id);

    /**
     * Получение всех баз
     * @return
     */
    List<BaseEntity> getAllBases();

    /**
     * Получение базы по id
     * @param id
     * @return
     */
    BaseEntity getBase(int id);

    /**
     * Получение всех систем
     * @return
     */
    Collection<SystemEntity> getAllSystems();

    /**
     * Получение системы по name
     * @param name
     * @return
     */
    SystemEntity getSystem(String name);

    /**
     * ПОлучение системы по id
     * @param id
     * @return
     */
    SystemEntity getSystem(int id);

    /**
     * Получение ресурса по id
     * @param id
     * @return
     */
    ResourceEntity getResource(int id);

    /**
     * Обновление ресурса
     * @param resourceEntity
     * @return
     */
    ResourceEntity updateResource(ResourceEntity resourceEntity);

    Collection<TypeShipEntity> getShipTypes();

}

package com.spaceRangers.service;

import com.spaceRangers.entities.*;

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
    ShipEntity getShipById(int idShip);

    /**
     * Создание корабля
     * @param ship
     * @return
     */
    ShipEntity createShip(ShipEntity ship);

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

}

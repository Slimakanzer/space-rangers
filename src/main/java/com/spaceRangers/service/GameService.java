package com.spaceRangers.service;

import com.spaceRangers.entities.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GameService {

    /**
     * Получение планеты
     * @param idPlanet
     * @return
     */
    PlanetEntity getPlanetById(int idPlanet);

    /**
     * Получение планеты по названию
     * @param name
     * @return
     */
    PlanetEntity getPlanetIdByName(String name);

    /**
     * Обновление планеты
     * например, когда кто-то её завоёвывает
     * @param planet
     * @return
     */
    boolean updatePlanet(PlanetEntity planet);

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
    boolean createBase(BaseEntity base);

    /**
     * Обновление базы
     * может смениться пользователь
     * @param base
     * @return
     */
    boolean updateBase(BaseEntity base);

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
    boolean createShip(ShipEntity ship);

    /**
     * Обновление  корабля
     * @param ship
     * @return
     */
    boolean updateShip(ShipEntity ship);


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
     * Получение кораблей пользователя в определенном состоянии
     * @param idUser
     * @param idStateShip
     * @return
     */
    List<ShipEntity> getListShypByIdUserAndIdStateShip(int idUser, int idStateShip);

}

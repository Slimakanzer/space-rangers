package com.spaceRangers.service;

import com.spaceRangers.entities.PlanetEntity;
import com.spaceRangers.entities.ResourceEntity;
import com.spaceRangers.entities.UsersEntity;

import java.util.List;

/**
 * Интерфейс сервиса управления планетами,
 * ресурсами планет, добычей ресурсов планет
 * @version 1.0
 * @author Ларочкин Г.И.
 */
public interface ConquestService {
    /**
     * Метод дает пользователя, который является обладателем данной планеты
     * @param planet {@link PlanetEntity сущность планеты}
     * @return возвращает {@link UsersEntity сущность пользователя}
     * @see PlanetEntity
     * @see UsersEntity
     */
    UsersEntity getOwnerPlanet(PlanetEntity planet);

    /**
     * Установка владельца планеты
     * @param planet {@link PlanetEntity сущность планеты}
     * @param idUser {@link UsersEntity#id идентификатор пользователя}
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see PlanetEntity
     * @see UsersEntity
     * @see UsersEntity#id
     */
    boolean setOwnerPlanet(PlanetEntity planet, int idUser);

    /**
     * Установка владельца планеты
     * @param planet {@link PlanetEntity сущность планеты}
     * @param loginUser {@link UsersEntity#login логин пользователя}
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see PlanetEntity
     * @see UsersEntity
     * @see UsersEntity#login
     */
    boolean setOwnerPlanet(PlanetEntity planet, String loginUser);

    /**
     * Установка владельца планеты
     * @param planet {@link PlanetEntity сущность планеты}
     * @param user {@link UsersEntity сущность пользователя}
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see PlanetEntity
     * @see UsersEntity
     */
    boolean setOwnerPlanet(PlanetEntity planet, UsersEntity user);

    /**
     * Удаление владельца планеты
     * @param planet {@link PlanetEntity сущщноасть планеты}
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see PlanetEntity
     * @see UsersEntity
     */
    boolean dropOwnerPlanet(PlanetEntity planet);

    /**
     * Список ресурсов планеты
     * @param planet {@link PlanetEntity сущноасть планеты}
     * @return возвращает список {@link ResourceEntity ресурсов}
     * @see PlanetEntity
     * @see ResourceEntity
     */
    List<ResourceEntity> getResourcesPlanet(PlanetEntity planet);

    /**
     * Удаление ресурса планеты
     * @param resource {@link ResourceEntity сущность ресурса}
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see ResourceEntity
     * @see PlanetEntity
     */
    boolean removeResourcesPlanet(ResourceEntity resource);
}

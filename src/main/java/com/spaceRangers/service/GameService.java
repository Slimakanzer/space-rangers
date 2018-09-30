package com.spaceRangers.service;

import com.spaceRangers.entities.*;

import java.util.List;

/**
 * Интерфейс сервиса взаимодействия с игрой
 * @version 1.0
 * @author Ларочкин Г.И.
 */
public interface GameService {

    /**
     * Вход в битву пользователем определенным кораблём
     * @param user {@link UsersEntity сущность пользователя}, который входит в битву
     * @param battle {@link BattleEntity сущность битвы}, в которую входит пользователь
     * @param ship {@link ShipEntity сущность корабля}, которым входит в битву
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see UsersEntity
     * @see BattleEntity
     * @see ShipEntity
     */
    boolean startBattleUsers(UsersEntity user, BattleEntity battle, ShipEntity ship);

    /**
     * Выход из битвы пользователем
     * @param user {@link UsersEntity сущность пользователя}, который выходит из битвы
     * @param battle {@link BattleEntity сущность битвы}, из которой выходит пользователь
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see UsersEntity
     * @see BattleEntity
     */
    boolean endBattleUsers(UsersEntity user, BattleEntity battle);

    /**
     * Получение списка кораблей пользователя, участвующих в битве
     * @param user {@link UsersEntity сущность пользователя}, корабли которого получаешь
     * @param battle {@link BattleEntity сущность битвы}, в которой необходимо получить корабли
     * @return возвращается список {@link ShipEntity сущностей кораблей}
     * @see UsersEntity
     * @see BattleEntity
     * @see ShipEntity
     */
    List<ShipEntity> getListUserBattleShip(UsersEntity user, BattleEntity battle);

    /**
     * Получение списка кораблей пользователя, участвующих в битве
     * @param idUser {@link UsersEntity#id идентификатор пользователя}, корабли которого получаешь
     * @param battle {@link BattleEntity сущность битвы}, в которой необходимо получить корабли
     * @return возвращается список {@link ShipEntity сущностей кораблей}
     * @see UsersEntity
     * @see UsersEntity#id
     * @see BattleEntity
     * @see ShipEntity
     */
    List<ShipEntity> getListUserBattleShip(int idUser, BattleEntity battle);

    /**
     * Получение списка кораблей пользователя, участвующих в битве
     * @param loginUser {@link UsersEntity#login логин пользователя}, корабли которого получаешь
     * @param battle {@link BattleEntity сущность битвы}, в которой необходимо получить корабли
     * @return возвращается список {@link ShipEntity сущностей кораблей}
     * @see UsersEntity
     * @see UsersEntity#login
     * @see BattleEntity
     * @see ShipEntity
     */
    List<ShipEntity> getListUserBattleShip(String loginUser, BattleEntity battle);

    /**
     * Получение списка кораблей противников в битве
     * @param user {@link UsersEntity сущность пользователя} для определения противников
     * @param battle {@link BattleEntity сущность битвы}, в которой необходимо получить корабли
     * @return возвращается список {@link ShipEntity сущностей кораблей}
     * @see UsersEntity
     * @see BattleEntity
     * @see ShipEntity
     */
    List<ShipEntity> getListEnemyBattleShip(UsersEntity user, BattleEntity battle);

    /**
     * Создание базы пользователем
     * @param user {@link UsersEntity сущность пользователя}, который создает базу
     * @param base {@link BaseEntity сущность базы}, которую необходимо создать
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see UsersEntity
     * @see BaseEntity
     */
    boolean createBase(UsersEntity user, BaseEntity base);

    /**
     * Получение списка баз пользователя
     * @param user {@link UsersEntity сущность пользователя}, у которого необходимо получить список
     * @return возвращается список {@link BaseEntity сущностей баз}
     * @see UsersEntity
     * @see BaseEntity
     */
    List<BaseEntity> getBaseList(UsersEntity user);

    /**
     * Получение списка баз пользователя
     * @param idUser {@link UsersEntity#id идентификатор пользователя}, у которого необходимо получить список
     * @return возвращается список {@link BaseEntity сущностей баз}
     * @see UsersEntity
     * @see UsersEntity#id
     * @see BaseEntity
     */
    List<BaseEntity> getBaseList(int idUser);

    /**
     * Получение списка баз пользователя
     * @param loginUser {@link UsersEntity#login логин пользователя}, у которого необходимо получить список
     * @return возвращается список {@link BaseEntity сущностей баз}
     * @see UsersEntity
     * @see UsersEntity#login
     * @see BaseEntity
     */
    List<BaseEntity> getBaseList(String loginUser);

    /**
     * Получение списка кораблей, которые привязаны к данной базе
     * @param base {@link BaseEntity сущность базы}, у которой необходимо определить корабли
     * @return возвращается список {@link ShipEntity сущностей кораблей}
     * @see BaseEntity
     * @see ShipEntity
     */
    List<ShipEntity> getShipsOfBase(BaseEntity base);

    /**
     * Получение владельца базы
     * @param base {@link BaseEntity сущность базы}, у которой необходимо определить владельца
     * @return возвращается {@link UsersEntity сущность пользователя}
     * @see BaseEntity
     * @see UsersEntity
     */
    UsersEntity getUserOfBase(BaseEntity base);

    /**
     * Обновление состояния сущности базы
     * @param base {@link BaseEntity сущность базы}, состояние которой обновляется
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see BaseEntity
     */
    boolean updateBase(BaseEntity base);

    /**
     * Получение сущности базы по её идентификатору
     * @param idBase {@link BaseEntity#id идентификатор базы}
     * @return возвращает {@link BaseEntity сущность базы}
     * @see BaseEntity
     * @see BaseEntity#id
     */
    BaseEntity getBase(int idBase);

    /**
     * Создание корабля пользователем
     * @param user {@link UsersEntity сущность пользователя}, который создает корабль
     * @param base {@link BaseEntity сущность базы}, к которой привязан корабль
     * @param ship {@link ShipEntity сущность корабля}, который создается
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see UsersEntity
     * @see BaseEntity
     * @see ShipEntity
     */
    boolean createShip(UsersEntity user, BaseEntity base, ShipEntity ship);

    /**
     * Обновление состояния корабля
     * @param ship {@link ShipEntity сущность корабля}, состояние которого необходимо обновить
     * @return возвращает true, если транзакция прошла успешно, иначе false
     * @see ShipEntity
     */
    boolean updateShip(ShipEntity ship);

    /**
     * Получение корабля по идентификатору
     * @param idShip {@link ShipEntity#id идентификатор корабля}
     * @return возвращается {@link ShipEntity сущность корабля}
     * @see ShipEntity
     * @see ShipEntity#id
     */
    ShipEntity getShipById(int idShip);

    /**
     * Получение спискаа ресурсов игрока
     * @param user {@link UsersEntity сущность пользователя}, ресурсы которого необходимо получить
     * @return возвращается список {@link ResourceEntity сущностей ресурсов}
     * @see UsersEntity
     * @see ResourceEntity
     */
    List<ResourceEntity> getResourcesUser(UsersEntity user);
}

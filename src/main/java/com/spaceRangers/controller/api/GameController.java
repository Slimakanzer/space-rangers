package com.spaceRangers.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spaceRangers.entities.*;
import com.spaceRangers.impl.FilterService;
import com.spaceRangers.repository.PlanetRepository;
import com.spaceRangers.service.GameService;
import com.spaceRangers.service.RegistrationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/api/game")
public class GameController {

    @Autowired
    GameService gameService;

    @Autowired
    FilterService filterService;

    @Autowired
    RegistrationService registrationService;

    @Autowired
    PlanetRepository planetRepository;


    @RequestMapping(value = "/ships", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_USER")
    public ResponseEntity getShips(){
            return ResponseEntity.ok(gameService.getAllShips());

    }


    @RequestMapping(value = "/ships/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_USER")
    public ResponseEntity getShip(
          @PathVariable int id
    ){
            try {
                ShipEntity shipEntity = gameService.getShip(id);
                return ResponseEntity.ok(shipEntity);
            }catch (NoSuchElementException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
    }


    @RequestMapping(value = "/bases", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_USER")
    public ResponseEntity getBases(){
            return ResponseEntity.ok(gameService.getAllBases());
    }


    @RequestMapping(value = "/bases/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_USER")
    public ResponseEntity getBase(
             @PathVariable int id
    ){
            try {
                BaseEntity baseEntity = gameService.getBase(id);
                return ResponseEntity.ok(baseEntity);
            }catch (NoSuchElementException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
    }

    @RequestMapping(value = "/systems", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getSystems(){
            return ResponseEntity.ok(gameService.getAllSystems());

    }


    @RequestMapping(value = "/systems/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getSystems(
            @PathVariable int id
    ){
            try {
                SystemEntity systemEntity = gameService.getSystem(id);
                return ResponseEntity.ok(systemEntity);
            }catch (NoSuchElementException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
    }


    @Secured("ROLE_USER")
    @RequestMapping(value = "/systems/{idSystem}/planets", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getPlanets(
             @PathVariable int idSystem
    ){
            try{
                SystemEntity systemEntity = gameService.getSystem(idSystem);
                List arrayList = Arrays.asList(systemEntity.getPlanets().toArray());
                Collections.reverse(arrayList);
                return ResponseEntity.ok(arrayList);

            }catch (NoSuchElementException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
    }


    @Secured("ROLE_USER")
    @RequestMapping(value = "/planets/{idPlanet}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getPlanet(
            @PathVariable int idPlanet
    ){
            try{
                PlanetEntity planetEntity = gameService.getPlanet(idPlanet);

                if(planetEntity == null)
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                else return ResponseEntity.ok(planetEntity);

            }catch (NoSuchElementException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
    }


    @Secured("ROLE_USER")
    @RequestMapping(value = "/planets/{idPlanet}/attack", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getShipsReady(
            @PathVariable int idPlanet,
            @AuthenticationPrincipal User user
    ){
        try{
            PlanetEntity planetEntity = gameService.getPlanet(idPlanet);
            UsersEntity usersEntity = registrationService.getUser(user);
            if(planetEntity != null){
                Collection<ShipEntity> readyShips = new ArrayList<>();

                usersEntity.getShips()
                        .forEach(ship ->{

                            double value = Math.sqrt(
                                    Math.pow(ship.getLocationShipX() - planetEntity.getLocationPlanetX()/100000, 2)
                                            +Math.pow(ship.getLocationShipY() - planetEntity.getLocationPlanetY()/100000, 2)
                                            +Math.pow(ship.getLocationShipZ() - planetEntity.getLocationPlanetZ()/100000, 2));

                            System.out.println(value);
                            if (value < 400) readyShips.add(ship);
                        });

                AttackPlanet attackPlanet = new AttackPlanet();

                if (readyShips.size() >=3) attackPlanet.setAttacked(true); else attackPlanet.setAttacked(false);

                attackPlanet.setCountShips(readyShips.size());
                return ResponseEntity.ok(attackPlanet);

            }
            return ResponseEntity.ok(HttpStatus.NOT_ACCEPTABLE);
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/planets/{idPlanet}/attack", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity attackPlanet(
           @PathVariable int idPlanet,
            @AuthenticationPrincipal User user
    ){
        try{
            PlanetEntity planetEntity = gameService.getPlanet(idPlanet);
            UsersEntity usersEntity = registrationService.getUser(user);

            planetEntity.setUser(usersEntity);


            return ResponseEntity.ok(planetRepository.save(planetEntity));
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/systems/{idSystem}/planets", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createPlanet(
             @PathVariable int idSystem,
            @RequestBody PlanetEntity planetEntity
    ){
            try {
                SystemEntity systemEntity = gameService.getSystem(idSystem);
                planetEntity.setSystem(systemEntity);

                gameService.createPlanet(planetEntity);

                return ResponseEntity.ok(planetEntity);
            }catch (NoSuchElementException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
    }


    @Secured("ROLE_USER")
    @RequestMapping(value = "/systems/{idSystem}/planets/{idPlanet}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updatePlanet(
            @PathVariable int idSystem,
            @PathVariable int idPlanet,
             @RequestBody PlanetEntity planetEntity
    ){
            try{
                SystemEntity systemEntity = gameService.getSystem(idSystem);
                planetEntity.setId(idPlanet);

                if(filterService.isPlanetInSystem(systemEntity, planetEntity)){
                    planetEntity.setSystem(systemEntity);
                    gameService.updatePlanet(planetEntity);
                    return ResponseEntity.ok(planetEntity);
                }

                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

            }catch (NoSuchElementException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
    }


    @Secured("ROLE_USER")
    @RequestMapping(value = "/systems/{idSystem}/planets/{idPlanet}/resources", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getResources(
             @PathVariable int idSystem,
            @PathVariable int idPlanet
    ){
            try{
                SystemEntity systemEntity = gameService.getSystem(idSystem);
                PlanetEntity planetEntity = gameService.getPlanet(idPlanet);
                if(filterService.isPlanetInSystem(systemEntity, planetEntity)) return ResponseEntity.ok(planetEntity.getResources());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

            }catch (NoSuchElementException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
    }


    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/systems/{idSystem}/planets/{idPlanet}/resources", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createResource(
           @PathVariable int idSystem,
            @PathVariable int idPlanet,
            @RequestBody ResourceEntity resourceEntity
    ){
            try{
                SystemEntity systemEntity = gameService.getSystem(idSystem);
                PlanetEntity planetEntity = gameService.getPlanet(idPlanet);
                if(filterService.isPlanetInSystem(systemEntity, planetEntity)){
                    resourceEntity.setPlanet(planetEntity);
                    gameService.createResource(resourceEntity);
                    return ResponseEntity.ok(resourceEntity);
                }

                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();


            }catch (NoSuchElementException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
    }


    @Secured("ROLE_USER")
    @RequestMapping(value = "/systems/{idSystem}/planets/{idPlanet}/resources/{idResource}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getResource(
            @PathVariable int idSystem,
            @PathVariable int idPlanet,
            @PathVariable int idResource
    ){
            try{
                SystemEntity systemEntity = gameService.getSystem(idSystem);
                PlanetEntity planetEntity = gameService.getPlanet(idPlanet);
                if(filterService.isPlanetInSystem(systemEntity, planetEntity)){
                    ResourceEntity resourceEntity = gameService.getResource(idResource);
                    if(filterService.isResourceInPlanet(planetEntity, resourceEntity)) return ResponseEntity.ok(resourceEntity);

                }
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

            }catch (NoSuchElementException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
    }


    @Secured("ROLE_USER")
    @RequestMapping(value = "/systems/{idSystem}/planets/{idPlanet}/resources/{idResource}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getResource(
            @PathVariable int idSystem,
            @PathVariable int idPlanet,
            @PathVariable int idResource,
            @RequestBody ResourceEntity resourceEntity,
            @AuthenticationPrincipal User user
    ){
            try{
                SystemEntity systemEntity = gameService.getSystem(idSystem);
                PlanetEntity planetEntity = gameService.getPlanet(idPlanet);
                if(filterService.isPlanetInSystem(systemEntity, planetEntity)){
                    resourceEntity.setId(idResource);
                    resourceEntity.setPlanet(planetEntity);
                    if(filterService.isResourceInPlanet(planetEntity, resourceEntity)){
                        if(filterService.isUsersPlanet(user, planetEntity)){
                            gameService.updateResource(resourceEntity);
                            return ResponseEntity.ok(resourceEntity);
                        }
                        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
                    }
                }
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

            }catch (NoSuchElementException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
    }


    @Secured("ROLE_USER")
    @RequestMapping(value = "/ships/types", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<TypeShipEntity>> getShipsTypes(){
        return ResponseEntity.ok(gameService.getShipTypes());
    }
}

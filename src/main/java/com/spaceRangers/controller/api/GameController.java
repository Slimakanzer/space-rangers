package com.spaceRangers.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spaceRangers.entities.*;
import com.spaceRangers.impl.FilterService;
import com.spaceRangers.service.GameService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/api/game")
public class GameController {

    @Autowired
    GameService gameService;

    @Autowired
    FilterService filterService;

    @ApiOperation("Get ships")
    @RequestMapping(value = "/ships", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_USER")
    public ResponseEntity getShips(){
        try{
            return ResponseEntity.ok(gameService.getAllShips());

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @ApiOperation("Get ship by id")
    @RequestMapping(value = "/ships/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_USER")
    public ResponseEntity getShip(
            @ApiParam("ship id") @PathVariable int id
    ){
        try{
            try {
                ShipEntity shipEntity = gameService.getShip(id);
                return ResponseEntity.ok(shipEntity);
            }catch (NoSuchElementException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @ApiOperation("Get bases")
    @RequestMapping(value = "/bases", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_USER")
    public ResponseEntity getBases(){
        try {
            return ResponseEntity.ok(gameService.getAllBases());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @ApiOperation("Get base by id")
    @RequestMapping(value = "/bases/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_USER")
    public ResponseEntity getBase(
            @ApiParam("base id") @PathVariable int id
    ){
        try{
            try {
                BaseEntity baseEntity = gameService.getBase(id);
                return ResponseEntity.ok(baseEntity);
            }catch (NoSuchElementException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @ApiOperation("Get systems")
    @RequestMapping(value = "/systems", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getSystems(){
        try{
            return ResponseEntity.ok(gameService.getAllSystems());

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @ApiOperation("Get system by id")
    @RequestMapping(value = "/systems/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getSystems(
            @ApiParam("system id") @PathVariable int id
    ){
        try{
            try {
                SystemEntity systemEntity = gameService.getSystem(id);
                return ResponseEntity.ok(systemEntity);
            }catch (NoSuchElementException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @ApiOperation("Get system planets")
    @Secured("ROLE_USER")
    @RequestMapping(value = "/systems/{idSystem}/planets", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getPlanets(
            @ApiParam("system id") @PathVariable int idSystem
    ){
        try{
            try{
                SystemEntity systemEntity = gameService.getSystem(idSystem);
                return ResponseEntity.ok(systemEntity.getPlanets());

            }catch (NoSuchElementException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @ApiOperation("Get planet by id")
    @Secured("ROLE_USER")
    @RequestMapping(value = "/systems/{idSystem}/planets/{idPlanet}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getPlanet(
            @ApiParam("system id")@PathVariable int idSystem,
            @ApiParam("planet id")@PathVariable int idPlanet
    ){
        try{
            try{
                SystemEntity systemEntity = gameService.getSystem(idSystem);
                PlanetEntity planetEntity = gameService.getPlanet(idPlanet);

                if(filterService.isPlanetInSystem(systemEntity, planetEntity)){
                    return ResponseEntity.ok(planetEntity);
                }
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

            }catch (NoSuchElementException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @ApiOperation("Create planet")
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/systems/{idSystem}/planets", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createPlanet(
            @ApiParam("system id") @PathVariable int idSystem,
            @ApiParam("Planet entity") @RequestBody PlanetEntity planetEntity
    ){
        try{
            try {
                SystemEntity systemEntity = gameService.getSystem(idSystem);
                planetEntity.setSystem(systemEntity);

                gameService.createPlanet(planetEntity);

                return ResponseEntity.ok(planetEntity);
            }catch (NoSuchElementException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @ApiOperation("Update planet")
    @Secured("ROLE_USER")
    @RequestMapping(value = "/systems/{idSystem}/planets/{idPlanet}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updatePlanet(
            @ApiParam("system id") @PathVariable int idSystem,
            @ApiParam("planet id") @PathVariable int idPlanet,
            @ApiParam("Planet entity") @RequestBody PlanetEntity planetEntity
    ){
        try{
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

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @ApiOperation("Get planet resources")
    @Secured("ROLE_USER")
    @RequestMapping(value = "/systems/{idSystem}/planets/{idPlanet}/resources", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getResources(
            @ApiParam("system id") @PathVariable int idSystem,
            @ApiParam("planet id") @PathVariable int idPlanet
    ){
        try{
            try{
                SystemEntity systemEntity = gameService.getSystem(idSystem);
                PlanetEntity planetEntity = gameService.getPlanet(idPlanet);
                if(filterService.isPlanetInSystem(systemEntity, planetEntity)) return ResponseEntity.ok(planetEntity.getResources());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

            }catch (NoSuchElementException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @ApiOperation("Create planet resource")
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/systems/{idSystem}/planets/{idPlanet}/resources", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createResource(
            @ApiParam("system id") @PathVariable int idSystem,
            @ApiParam("planet id") @PathVariable int idPlanet,
            @ApiParam("Resource entity") @RequestBody ResourceEntity resourceEntity
    ){
        try{
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
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @ApiOperation("Get resource by id")
    @Secured("ROLE_USER")
    @RequestMapping(value = "/systems/{idSystem}/planets/{idPlanet}/resources/{idResource}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getResource(
            @ApiParam("system id") @PathVariable int idSystem,
            @ApiParam("planet id") @PathVariable int idPlanet,
            @ApiParam("resource id") @PathVariable int idResource
    ){
        try{

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

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @ApiOperation("Update count of resource")
    @Secured("ROLE_USER")
    @RequestMapping(value = "/systems/{idSystem}/planets/{idPlanet}/resources/{idResource}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getResource(
            @ApiParam("system id") @PathVariable int idSystem,
            @ApiParam("planet id") @PathVariable int idPlanet,
            @ApiParam("resource id") @PathVariable int idResource,
            @ApiParam("Resource entity") @RequestBody ResourceEntity resourceEntity,
            @ApiIgnore @AuthenticationPrincipal User user
    ){
        try{

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

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }



}
